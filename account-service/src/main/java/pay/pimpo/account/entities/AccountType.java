
package pay.pimpo.account.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Tipos de conta.
 *
 * @author fabio.tasco
 */
@Entity
public class AccountType implements Serializable {

	private static final long serialVersionUID = 2892461545225131845L;

	@Id
	@GeneratedValue
	private Integer Id;

	@Column(length = 50)
	private String type;

	AccountType() {}

	public AccountType(final String type) {
		this.type = type;
	}

	public Integer getId() {
		return Id;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (type == null ? 0 : type.hashCode());
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
		final AccountType other = (AccountType) obj;
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return type;
	}

}
