
package pay.pimpo.api.gateway.rules;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AuthClient;

@Component
public class AuthenticationRouterRules {

	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private AuthClient authClient;

	public Response<Long> routeAuthentication(final HttpServletRequest request) {
		return authClient.authenticate(request.getHeader(AUTHORIZATION));
	}

}
