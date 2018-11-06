
package pay.pimpo.commons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Contrato vinculado a conta.
 *
 * @author fabio.tasco
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "contractType", "account_id" }) })
public class AccountContract implements Serializable {

	private static final long serialVersionUID = 2892461545225131845L;

	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 55, nullable = false)
	private AccountContractType contractType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;

	AccountContract() {}

	public AccountContract(final AccountContractType contractType, final Account account) {
		this.contractType = contractType;
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public AccountContractType getContractType() {
		return contractType;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (contractType == null ? 0 : contractType.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AccountContract other = (AccountContract) obj;
		if (contractType != other.contractType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return contractType.name();
	}

}
