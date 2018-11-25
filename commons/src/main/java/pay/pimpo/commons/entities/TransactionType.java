
package pay.pimpo.commons.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import pay.pimpo.commons.utils.ApiUtils;

public enum TransactionType {

	/** Compra */
	PURCHASE,

	/** Depósito na conta corrente */
	DEPOSIT,

	/** Transferência entre contas */
	TRANSFER,

	/** Débito automático */
	AUTOMATIC_DEBIT,

	/** Saque */
	WITHDRAWAL,

	/** Recarga de celular */
	MOBILE_RECHARGE;

	@JsonValue
	public String value() {
		return ApiUtils.formatEnumName(name());
	}

}
