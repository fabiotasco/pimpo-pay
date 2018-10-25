
package pay.pimpo.api.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import feign.FeignException;
import pay.pimpo.api.gateway.rules.AuthenticationRouterRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.exceptions.MicroserviceNotFoundException;
import pay.pimpo.commons.exceptions.UnauthorizedAccessException;

/**
 * Filtro que roteia a autenticação do usuário.
 *
 * @author fabio.tasco
 */
public class AuthenticationRouterFilter extends ZuulFilter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationRouterFilter.class);

	@Autowired
	private AuthenticationRouterRules authenticationRouterRules;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		final RequestContext context = RequestContext.getCurrentContext();
		try {
			final Response<Long> response = authenticationRouterRules.routeAuthentication(context.getRequest());
			if (response.isSuccess()) {
				context.addZuulRequestHeader("x-user-id", response.getContent().toString());
			}
		} catch (final FeignException e) {
			LOG.error("Error while routing authentication!", e);
			if (e.status() == 404) {
				throw new MicroserviceNotFoundException(e.getMessage());
			}
			if (e.status() == 401) {
				throw new UnauthorizedAccessException();
			}
		}
		return context;
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
