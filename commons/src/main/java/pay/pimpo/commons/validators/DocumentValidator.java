
package pay.pimpo.commons.validators;

import java.util.InputMismatchException;

import pay.pimpo.commons.entities.DocumentType;
import pay.pimpo.commons.exceptions.InvalidDocumentFormatException;

/**
 * Validador de documentos.
 *
 * @author fabio.tasco
 */
public class DocumentValidator {

	/**
	 * Valida documentos do tipo CPF e CNPJ.
	 *
	 * @param document Dígitos do documento.
	 * @param documentType Tipo do documento.
	 * @return Verdadeiro, se for o documento informado for válido. Falso, caso contrário.
	 * @throws InvalidDocumentFormatException Quando o documento for inválido.
	 */
	public static boolean validate(final String document, final DocumentType documentType)
		throws InvalidDocumentFormatException {
		switch (documentType) {
			case CPF:
				return validateCpf(document);
			case CNPJ:
				return validateCnpj(document);
			default:
				throw new UnsupportedOperationException("Document type validation unsupported: " + documentType);
		}
	}

	private static boolean validateCpf(final String cpf) throws InvalidDocumentFormatException {
		if (cpf.equals("00000000000") || cpf.equals("11111111111")
			|| cpf.equals("22222222222")
			|| cpf.equals("33333333333")
			|| cpf.equals("44444444444")
			|| cpf.equals("55555555555")
			|| cpf.equals("66666666666")
			|| cpf.equals("77777777777")
			|| cpf.equals("88888888888")
			|| cpf.equals("99999999999")) {
			throw new InvalidDocumentFormatException("CPF cannot be composed by a sequence of the same number: " + cpf);
		}
		if (cpf.length() != 11) {
			throw new InvalidDocumentFormatException("CPF length must be 11 digits: " + cpf);
		}

		char tenthDigit, eleventhDigit;
		int sum, index, r, number, weight;

		try {
			// Calcula o 1o dígito verificador
			sum = 0;
			weight = 10;
			for (index = 0; index < 9; index++) {
				// Converte o i-ésimo caractere do CPF em um número, por exemplo, transforma o caractere '0' no inteiro
				// 0 (48 eh a posicao de '0' na tabela ASCII)
				number = cpf.charAt(index) - 48;
				sum = sum + number * weight;
				weight = weight - 1;
			}

			r = 11 - sum % 11;
			if (r == 10 || r == 11) {
				tenthDigit = '0';
			} else {
				tenthDigit = (char) (r + 48); // converte no respectivo caractere numérico
			}

			// Calculo do 2o dígito verificador
			sum = 0;
			weight = 11;
			for (index = 0; index < 10; index++) {
				number = cpf.charAt(index) - 48;
				sum = sum + number * weight;
				weight = weight - 1;
			}

			r = 11 - sum % 11;
			if (r == 10 || r == 11) {
				eleventhDigit = '0';
			} else {
				eleventhDigit = (char) (r + 48);
			}

			// Verifica se os dígitos calculados conferem com os dígitos informados.
			if (tenthDigit == cpf.charAt(9) && eleventhDigit == cpf.charAt(10)) {
				return true;
			} else {
				throw new InvalidDocumentFormatException("CPF is invalid: " + cpf);
			}
		} catch (final InputMismatchException e) {
			throw new InvalidDocumentFormatException("CPF is invalid: " + cpf, e);
		}
	}

	private static boolean validateCnpj(final String cnpj) throws InvalidDocumentFormatException {
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
			|| cnpj.equals("22222222222222")
			|| cnpj.equals("33333333333333")
			|| cnpj.equals("44444444444444")
			|| cnpj.equals("55555555555555")
			|| cnpj.equals("66666666666666")
			|| cnpj.equals("77777777777777")
			|| cnpj.equals("88888888888888")
			|| cnpj.equals("99999999999999")) {
			throw new InvalidDocumentFormatException(
				"CNPJ cannot be composed by a sequence of the same number: " + cnpj);
		}
		if (cnpj.length() != 14) {
			throw new InvalidDocumentFormatException("CNPJ length must be 11 digits: " + cnpj);
		}

		char thirteenthDigit, fourteenthDigit;
		int sum, index, r, number, weight;

		try {
			// Calcula do 1o dígito verificador
			sum = 0;
			weight = 2;
			for (index = 11; index >= 0; index--) {
				// Converte o i-ésimo caractere do CNPJ em um número, por exemplo, transforma o caractere '0' no inteiro
				// 0 (48 eh a posição de '0' na tabela ASCII)
				number = cnpj.charAt(index) - 48;
				sum = sum + number * weight;
				weight = weight + 1;
				if (weight == 10) {
					weight = 2;
				}
			}

			r = sum % 11;
			if (r == 0 || r == 1) {
				thirteenthDigit = '0';
			} else {
				thirteenthDigit = (char) (11 - r + 48);
			}

			// Calcula do 2o dígito verificador
			sum = 0;
			weight = 2;
			for (index = 12; index >= 0; index--) {
				number = cnpj.charAt(index) - 48;
				sum = sum + number * weight;
				weight = weight + 1;
				if (weight == 10) {
					weight = 2;
				}
			}

			r = sum % 11;
			if (r == 0 || r == 1) {
				fourteenthDigit = '0';
			} else {
				fourteenthDigit = (char) (11 - r + 48);
			}

			// Verifica se os dígitos calculados conferem com os dígitos informados.
			if (thirteenthDigit == cnpj.charAt(12) && fourteenthDigit == cnpj.charAt(13)) {
				return true;
			} else {
				throw new InvalidDocumentFormatException("CNPJ is invalid: " + cnpj);
			}
		} catch (final InputMismatchException e) {
			throw new InvalidDocumentFormatException("CNPJ is invalid: " + cnpj, e);
		}
	}

}
