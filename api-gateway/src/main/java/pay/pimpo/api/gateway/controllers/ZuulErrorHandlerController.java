
package pay.pimpo.api.gateway.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.api.gateway.rules.ZuulErrorHandlerRules;
import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.controllers.ErrorHandlerController;

/**
 * Tratamento de erros específicos ocasionados no Zuul e quando houver trocas de mensagens via Feign Client. Os erros
 * internos de cada microsserviço são tratados pelo {@link ErrorHandlerController}.
 *
 * @author fabio.tasco
 * @see ErrorHandlerController
 */
@RestController
public class ZuulErrorHandlerController extends AbstractErrorController {

	@Autowired
	private ZuulErrorHandlerRules zuulErrorHandlerRules;

	@Value("${error.path:/error}")
	private String errorPath;

	public ZuulErrorHandlerController(final ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(value = "${error.path:/error}", produces = "application/json;charset=UTF-8")
	public Response<?> error(final HttpServletRequest request) {
		return new Response<>(zuulErrorHandlerRules.handleZuulError(request));
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}

}
