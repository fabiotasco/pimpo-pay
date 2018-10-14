
package pay.pimpo.enroll.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AccountClient;
import pay.pimpo.commons.clients.NetworkOperatorClient;
import pay.pimpo.commons.clients.UserClient;
import pay.pimpo.commons.dto.CreateAccountDto;
import pay.pimpo.commons.dto.CreateUserDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.exceptions.InvalidDocumentException;
import pay.pimpo.commons.exceptions.InvalidPhoneException;
import pay.pimpo.commons.exceptions.NetworkOperatorNotFoundException;
import pay.pimpo.commons.validators.DocumentValidator;
import pay.pimpo.commons.validators.PhoneValidator;
import pay.pimpo.enroll.dto.EnrollDto;

@Component
public class EnrollRules {

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

	public Response<Account> enroll(final EnrollDto enrollDto) throws Exception {
		validateEnrollData(enrollDto);
		final Long userId = createNewUser(enrollDto);

		// TODO: Se houver problema ao criar a conta, é necessário deletar o usuário criado anteriormente (userId).
		return createNewAccount(enrollDto, userId);
	}

	private void validateEnrollData(final EnrollDto enrollDto)
		throws InvalidDocumentException,
		InvalidPhoneException,
		NetworkOperatorNotFoundException {

		documentValidator.validate(enrollDto.getDocumentDto().getValue(), enrollDto.getDocumentDto().getType());
		phoneValidator.validatePhone(enrollDto.getPhoneDto().getNumber());
		networkOperatorClient.findByName(enrollDto.getPhoneDto().getNetworkOperator());
	}

	private Long createNewUser(final EnrollDto enrollDto) {
		final CreateUserDto createUserDto
			= new CreateUserDto(enrollDto.getDocumentDto().getValue(), enrollDto.getPassword());

		return userClient.createUser(createUserDto).getContent();
	}

	private Response<Account> createNewAccount(final EnrollDto enrollDto, final Long userId) {
		final CreateAccountDto createAccountDto
			= new CreateAccountDto(enrollDto.getDocumentDto(), enrollDto.getPhoneDto(), userId);

		return accountClient.createAccount(createAccountDto);
	}

}
