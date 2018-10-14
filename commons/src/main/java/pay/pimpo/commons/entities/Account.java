
package pay.pimpo.commons.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	@Column(nullable = false, length = 256, unique = true)
	private String hash;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AccountType type;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "account_plans",
		joinColumns = { @JoinColumn(name = "account_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "plan_id", referencedColumnName = "id") })
	private List<AccountPlan> plans;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "account")
	private List<AccountNumber> numbers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AccountStatus status;

	/** Chave lógica para a tabela User. */
	@Column(nullable = false)
	private Long userId;

	Account() {}

	public Account(final String hash, final AccountType type, final AccountStatus status, final Long userId) {
		this.hash = hash;
		this.type = type;
		plans = new ArrayList<>(1);
		numbers = new ArrayList<>(1);
		this.status = status;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public AccountType getType() {
		return type;
	}

	public List<AccountPlan> getPlans() {
		return plans;
	}

	public List<AccountNumber> getNumbers() {
		return numbers;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (numbers == null ? 0 : numbers.hashCode());
		result = prime * result + (hash == null ? 0 : hash.hashCode());
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
		if (numbers == null) {
			if (other.numbers != null) {
				return false;
			}
		} else if (!numbers.equals(other.numbers)) {
			return false;
		}
		if (hash == null) {
			if (other.hash != null) {
				return false;
			}
		} else if (!hash.equals(other.hash)) {
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
			+ ", hash="
			+ hash
			+ ", type="
			+ type
			+ ", status="
			+ status
			+ ", userId="
			+ userId
			+ ", numbers="
			+ numbers
			+ "]";
	}

}
