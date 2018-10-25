
package pay.pimpo.api.gateway.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.commons.api.Error;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.api.StandardErrors;
import pay.pimpo.commons.builders.ErrorBuilder;
import pay.pimpo.commons.exceptions.PimpoPayException;

@RestController
public class ErrorHandlerController extends AbstractErrorController {

	@Value("${error.path:/error}")
	private String errorPath;

	public ErrorHandlerController(final ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}

	@RequestMapping(value = "${error.path:/error}", produces = "application/json;charset=UTF-8")
	public Response<?> error(final HttpServletRequest request) {
		return new Response<>(findStandardError(request));
	}

	private Error findStandardError(final HttpServletRequest request) {
		final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
		final Throwable cause = exc.getCause();
		if (cause != null) {
			if (cause instanceof PimpoPayException) {
				return ((PimpoPayException) cause).getError();
			} else {
				return new ErrorBuilder().setCode("GEN-9999").setMessage(cause.getMessage()).build();
			}
		}
		return StandardErrors.INTERNAL_SERVER_ERROR;
	}

}
