
package pay.pimpo.auth.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = -5259064374398487078L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "permission_role",
		joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private List<Permission> permissions;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updatedAt;

	public Role() {}

	@PrePersist
	protected void prePersist() {
		createdAt = updatedAt = new Date();
	}

	@PreUpdate
	protected void preUpdate() {
		updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
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
		final Role other = (Role) obj;
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
		return "Role [name=" + name + "]";
	}

}
