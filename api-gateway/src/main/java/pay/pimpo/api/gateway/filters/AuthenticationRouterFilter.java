
package pay.pimpo.api.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import pay.pimpo.api.gateway.rules.AuthenticationRouterRules;

/**
 * Filtro que roteia a autenticação do usuário.
 *
 * @author fabio.tasco
 */
public class AuthenticationRouterFilter extends ZuulFilter {

	@Autowired
	private AuthenticationRouterRules authenticationRouterRules;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		return authenticationRouterRules.routeAuthentication(RequestContext.getCurrentContext().getRequest());
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
