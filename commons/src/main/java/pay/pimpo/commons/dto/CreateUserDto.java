
package pay.pimpo.commons.dto;

/**
 * DTO utilizado para a criação de usuário.
 *
 * @author fabio.tasco
 * @see auth-service#AuthController#createUser
 */
public class CreateUserDto {

	private String username;
	private String password;

	CreateUserDto() {}

	public CreateUserDto(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
