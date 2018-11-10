
package pay.pimpo.commons.entities;

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

}
