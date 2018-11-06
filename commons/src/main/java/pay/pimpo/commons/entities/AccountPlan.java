
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
 * Tipos de plano.
 *
 * @author fabio.tasco
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "planType", "account_id" }) })
public class AccountPlan implements Serializable {

	private static final long serialVersionUID = -693488986847016161L;

	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 55, nullable = false)
	private PlanType planType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;

	AccountPlan() {}

	public AccountPlan(final PlanType planType, final Account account) {
		this.planType = planType;
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (planType == null ? 0 : planType.hashCode());
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
		final AccountPlan other = (AccountPlan) obj;
		if (planType != other.planType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return planType.name();
	}

}
