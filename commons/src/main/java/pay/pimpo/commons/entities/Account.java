
package pay.pimpo.commons.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Conta corrente.
 *
 * @author fabio.tasco
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Account implements Serializable {

	private static final long serialVersionUID = 7820574512444005096L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 64, unique = true)
	private String hash;

	@Column(nullable = false, precision = 12, scale = 2)
	private Double balance;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "account")
	private List<AccountContract> contracts;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "account")
	private List<AccountPlan> plans;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "account")
	private List<AccountNumber> numbers;

	@Enumerated(EnumType.STRING)
	@Column(length = 55, nullable = false)
	private AccountStatus status;

	// TODO: Criar o cadastro para ser merchant businessEnabled e limite de crédito e data de vencimento da fatura.

	/** Chave lógica para a tabela User. */
	@Column(nullable = false)
	private Long userId;

	Account() {}

	public Account(final String hash, final Double balance, final AccountStatus status, final Long userId) {
		this.hash = hash;
		this.balance = balance;
		contracts = new ArrayList<>(1);
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(final Double balance) {
		this.balance = balance;
	}

	public List<AccountContract> getContracts() {
		return contracts;
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
		result = prime * result + (hash == null ? 0 : hash.hashCode());
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
		if (hash == null) {
			if (other.hash != null) {
				return false;
			}
		} else if (!hash.equals(other.hash)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id
			+ ", hash="
			+ hash
			+ ", balance="
			+ balance
			+ ", status="
			+ status
			+ ", userId="
			+ userId
			+ "]";
	}

}
