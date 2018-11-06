
package pay.pimpo.commons.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import pay.pimpo.commons.utils.ApiUtils;

/**
 * Status de uma conta.
 *
 * @author fabio.tasco
 */
public enum AccountStatus {

	ACTIVE,
	INACTIVE;

	@JsonValue
	public String value() {
		return ApiUtils.formatEnumName(name());
	}

}
