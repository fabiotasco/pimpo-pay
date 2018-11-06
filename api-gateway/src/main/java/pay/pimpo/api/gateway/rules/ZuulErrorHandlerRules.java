
package pay.pimpo.api.gateway.rules;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.exception.ZuulException;

import pay.pimpo.api.gateway.filters.AuthenticationRouterFilter;
import pay.pimpo.commons.api.Error;
import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.builders.ErrorBuilder;
import pay.pimpo.commons.exceptions.PimpoPayException;

@Component
public class ZuulErrorHandlerRules {

	private static final String JAVAX_SERVLET_ERROR_EXCEPTION = "javax.servlet.error.exception";
	private static final String JAVAX_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";

	public Error handleZuulError(final HttpServletRequest request) {
		final Object error = request.getAttribute(AuthenticationRouterFilter.ERROR_ATTRIBUTE);
		if (error != null) {
			return (Error) error;
		}

		final Throwable throwable = (Throwable) request.getAttribute(JAVAX_SERVLET_ERROR_EXCEPTION);
		int status = (int) request.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE);

		if (throwable == null) {
			if (status == 404) {
				return StandardErrors.RESOURCE_NOT_FOUND;
			}
			return StandardErrors.INTERNAL_SERVER_ERROR;
		}

		final Throwable throwableCause = throwable.getCause();
		if (throwableCause != null) {
			if (throwableCause instanceof PimpoPayException) {
				return ((PimpoPayException) throwableCause).getError();
			}
			return new ErrorBuilder().setCode("GEN-0000").setMessage(throwableCause.getMessage()).build();
		}

		if (throwable instanceof ZuulException) {
			status = ((ZuulException) throwable).nStatusCode;
			final String message = ((ZuulException) throwable).errorCause;
			return new ErrorBuilder().setCode("GEN-0" + status).setMessage(message).build();
		}
		return StandardErrors.INTERNAL_SERVER_ERROR;
	}

}
