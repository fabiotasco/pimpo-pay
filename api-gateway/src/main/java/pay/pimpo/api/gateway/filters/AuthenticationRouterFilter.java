
package pay.pimpo.api.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import feign.FeignException;
import pay.pimpo.api.gateway.rules.AuthenticationRouterRules;
import pay.pimpo.commons.api.Error;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.clients.AuthClient;

/**
 * Filtro que roteia a autenticação do usuário.
 *
 * @author fabio.tasco
 */
public class AuthenticationRouterFilter extends ZuulFilter {

	public static final String ERROR_ATTRIBUTE = "ErrorAttribute";

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
				context.addZuulRequestHeader(AuthClient.USER_ID_HEADER_KEY, response.getContent().toString());
			}
		} catch (final FeignException e) {
			LOG.error("Error while routing authentication!", e);
			if (e.status() == 404) {
				addRequestAttributeError(context, StandardErrors.MICROSERVICE_NOT_FOUND);
			} else if (e.status() == 401) {
				addRequestAttributeError(context, StandardErrors.UNAUTHORIZED_ACCESS);
			}
		}
		return context;
	}

	private void addRequestAttributeError(final RequestContext context, final Error error) {
		context.getRequest().setAttribute(ERROR_ATTRIBUTE, error);
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
