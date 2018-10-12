
package pay.pimpo.account.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Status de um número de telefone vinculado a uma conta.
 *
 * @author fabio.tasco
 */
@Entity
public class AccountNumberStatus implements Serializable {

	private static final long serialVersionUID = -4821832107297437458L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 50)
	private String status;

	AccountNumberStatus() {}

	public AccountNumberStatus(final String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (status == null ? 0 : status.hashCode());
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
		final AccountNumberStatus other = (AccountNumberStatus) obj;
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return status;
	}

}
