
package pay.pimpo.account.rules;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.dto.DocumentDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.commons.entities.AccountContract;

@Component
public class AccountContractRules {

	// FIXME: Remover! - Método não é mais utilizado!
	// @Autowired
	// private AccountContractRepository accountContractRepository;
	//
	// /**
	// * Busca pelo tipo de conta.
	// *
	// * @param contractType O tipo de conta.
	// * @return O tipo de conta.
	// * @throws AccountContractNotFoundException Quando não encontrar o tipo de conta informado.
	// */
	// public AccountContract findAccountContract(final AccountContractType contractType)
	// throws AccountContractNotFoundException {
	//
	// final AccountContract accountContract = accountContractRepository.findByContractType(contractType);
	// if (accountContract == null) {
	// throw new AccountContractNotFoundException(contractType);
	// }
	// return accountContract;
	// }

	public AccountContract createContract(final DocumentDto documentDto, final Account account) {
		return new AccountContract(documentDto.getType().getAssociatedContractType(), account);
	}

}
