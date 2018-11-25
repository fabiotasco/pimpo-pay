
package pay.pimpo.access.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.access.dto.EnrollDto;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.clients.NetworkOperatorClient;
import pay.pimpo.commons.clients.UserClient;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.dto.CreateUserDto;
import pay.pimpo.commons.dto.LoginDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.NetworkOperator;
import pay.pimpo.commons.exceptions.InvalidDocumentFormatException;
import pay.pimpo.commons.exceptions.InvalidPhoneException;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;
import pay.pimpo.commons.validators.DocumentValidator;
import pay.pimpo.commons.validators.PhoneValidator;

@Component
public class AccessRules {

	@Autowired
	private NetworkOperatorClient networkOperatorClient;

	@Autowired
	private AccountClient accountClient;

	@Autowired
	private UserClient userClient;

	@Autowired
	private DocumentValidator documentValidator;

	@Autowired
	private PhoneValidator phoneValidator;

	public Response<Account> login(final LoginDto loginDto) {
		final Response<Long> userLoginResponse = userClient.login(loginDto);
		if (!userLoginResponse.isSuccess()) {
			return new Response<>(userLoginResponse.getErrors());
		}
		return accountClient.findByUserId(userLoginResponse.getContent());
	}

	public Response<Account> enroll(final EnrollDto enrollDto) throws Exception {
		final Response<NetworkOperator> validateResponse = validateEnrollData(enrollDto);
		if (!validateResponse.isSuccess()) {
			return new Response<>(validateResponse.getErrors());
		}

		// Cria o usuário
		final Response<Long> createUserResponse = createUser(enrollDto);
		if (!createUserResponse.isSuccess()) {
			return new Response<>(createUserResponse.getErrors());
		}

		// Cria a conta
		final Long userId = createUserResponse.getContent();
		final Response<Account> createAccountResponse = createNewAccount(enrollDto, userId);
		if (!createAccountResponse.isSuccess()) {
			// Desfaz a criação do usuário, caso ocorra algum problema ao criar a conta
			userClient.deleteUser(userId);
		}
		return createAccountResponse;
	}

	private Response<NetworkOperator> validateEnrollData(final EnrollDto enrollDto)
		throws InvalidDocumentFormatException,
		InvalidPhoneException,
		NetworkOperatorNotFoundException {

		documentValidator.validate(enrollDto.getDocumentDto().getValue(), enrollDto.getDocumentDto().getType());
		phoneValidator.validateNumber(enrollDto.getPhoneDto().getNumber());

		return networkOperatorClient.findByName(enrollDto.getPhoneDto().getNetworkOperator());
	}

	private Response<Long> createUser(final EnrollDto enrollDto) throws Exception {
		final CreateUserDto createUserDto
			= new CreateUserDto(enrollDto.getDocumentDto().getValue(), enrollDto.getPassword());

		return userClient.createUser(createUserDto);
	}

	private Response<Account> createNewAccount(final EnrollDto enrollDto, final Long userId) throws Exception {
		final CreateAccountDto createAccountDto
			= new CreateAccountDto(enrollDto.getDocumentDto(), enrollDto.getPhoneDto(), userId);

		return accountClient.createAccount(createAccountDto);
	}

}
