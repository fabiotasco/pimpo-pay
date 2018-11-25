
package pay.pimpo.commons.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import pay.pimpo.commons.utils.ApiUtils;

public enum TransactionStatus {

	/** Autorizada */
	AUTHORIZED,

	/** Negada */
	DENIED,

	/** Cancelada */
	CANCELED,

	/** Liquidada */
	SETTLED,

	/** Disputada */
	DISPUTED,

	/** Disputa respondida */
	DISPUTE_RESPONDED,

	/** Arbitrada */
	ARBITRATED;

	@JsonValue
	public String value() {
		return ApiUtils.formatEnumName(name());
	}

}
