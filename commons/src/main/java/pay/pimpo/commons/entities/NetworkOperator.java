
package pay.pimpo.commons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Operadora de telefonia móvel, fixa ou de rede.
 *
 * @author fabio.tasco
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NetworkOperator implements Serializable {

	private static final long serialVersionUID = -1156807457090825484L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	NetworkOperator() {}

	public NetworkOperator(final String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
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
		final NetworkOperator other = (NetworkOperator) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
