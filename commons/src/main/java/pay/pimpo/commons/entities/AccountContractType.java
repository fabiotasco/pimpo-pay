
package pay.pimpo.commons.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import pay.pimpo.commons.utils.ApiUtils;

/**
 * Tipos de contrato suportados.
 *
 * @author fabio.tasco
 */
public enum AccountContractType {

	PERSONAL,
	BUSINESS;

	@JsonValue
	public String value() {
		return ApiUtils.formatEnumName(name());
	}

}
