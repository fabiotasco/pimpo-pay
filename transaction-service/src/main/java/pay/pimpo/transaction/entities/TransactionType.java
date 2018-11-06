
package pay.pimpo.transaction.entities;

public enum TransactionType {

	/** Compra */
	PURCHASE,

	/** Creditar conta corrente */
	CREDIT,

	/** Transferência entre contas */
	TRANSFER,

	/** Débito automático */
	AUTOMATIC_DEBIT,

	/** Saque */
	WITHDRAWAL,

	/** Recarga de celular */
	MOBILE_RECHARGE,

	/** Cancelamento de transação */
	CANCEL;

}
