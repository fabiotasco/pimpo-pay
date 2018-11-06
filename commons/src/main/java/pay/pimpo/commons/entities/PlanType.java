
package pay.pimpo.commons.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import pay.pimpo.commons.utils.ApiUtils;

/**
 * Tipos de planos suportados.
 *
 * @author fabio.tasco
 */
public enum PlanType {

	PREPAID,
	CREDIT;

	@JsonValue
	public String value() {
		return ApiUtils.formatEnumName(name());
	}

}
