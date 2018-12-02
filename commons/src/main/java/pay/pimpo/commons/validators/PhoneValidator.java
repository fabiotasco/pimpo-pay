
package pay.pimpo.commons.validators;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.exceptions.InvalidPhoneException;

/**
 * Validador do formator do número de telefone e de operadora.
 *
 * @author fabio.tasco
 */
@Component
public class PhoneValidator {

	/**
	 * Valida o formato de telefone brasileiro: +55DDxxxxxyyyy.
	 *
	 * @param phone Número de telefone
	 * @return Verdadeiro, se estiver no formato brasileiro. Falso, caso contrário.
	 * @throws InvalidPhoneException Caso seja um número de telefone inválido.
	 */
	public boolean validateNumber(final String phone) throws InvalidPhoneException {
		// Todos os telefones devem começar com o sinal de +
		if (phone.charAt(0) != '+') {
			throw new InvalidPhoneException("Phone number must start with '+' sign: " + phone);
		}
		if (phone.length() != 14) {
			throw new InvalidPhoneException(
				"Phone length must be 14 with the following format '+55DDxxxxxxxxx' : " + phone);
		}
		return true;
	}

}
