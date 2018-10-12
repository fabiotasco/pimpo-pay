
package pay.pimpo.account.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Conta corrente.
 *
 * @author fabio.tasco
 */
@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 7820574512444005096L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<AccountNumber> accountNumbers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AccountType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AccountStatus status;

	/** Chave lógica para a tabela User. */
	@Column(nullable = false)
	private Long userId;

	Account() {}

	public Account(
		final List<AccountNumber> accountNumbers,
		final AccountType type,
		final AccountStatus status,
		final Long userId) {
		this.accountNumbers = accountNumbers;
		this.type = type;
		this.status = status;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public List<AccountNumber> getAccountNumbers() {
		return accountNumbers;
	}

	public void setAccountNumbers(final List<AccountNumber> accountNumbers) {
		this.accountNumbers = accountNumbers;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(final AccountType type) {
		this.type = type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(final AccountStatus status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accountNumbers == null ? 0 : accountNumbers.hashCode());
		result = prime * result + (type == null ? 0 : type.hashCode());
		result = prime * result + (userId == null ? 0 : userId.hashCode());
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
		final Account other = (Account) obj;
		if (accountNumbers == null) {
			if (other.accountNumbers != null) {
				return false;
			}
		} else if (!accountNumbers.equals(other.accountNumbers)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id
			+ ", accountNumbers="
			+ accountNumbers
			+ ", type="
			+ type
			+ ", status="
			+ status
			+ ", userId="
			+ userId
			+ "]";
	}

}
