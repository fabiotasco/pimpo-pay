
package pay.pimpo.api.gateway.rules;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.clients.AuthClient;

@Component
public class AuthenticationRouterRules {

	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private AuthClient authClient;

	public ResponseEntity<Void> routeAuthentication(final HttpServletRequest request) {
		return authClient.auth(request.getHeader(AUTHORIZATION));
	}

}
