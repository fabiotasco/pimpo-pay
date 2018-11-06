
package pay.pimpo.commons.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import pay.pimpo.commons.utils.ApiUtils;

/**
 * Status de um número de telefone vinculado a uma conta.
 *
 * @author fabio.tasco
 */
public enum AccountNumberStatus {

	ACTIVE,
	INACTIVE;

	@JsonValue
	public String value() {
		return ApiUtils.formatEnumName(name());
	}

}
