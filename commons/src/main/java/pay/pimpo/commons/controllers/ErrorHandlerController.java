
package pay.pimpo.commons.controllers;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.exceptions.PimpoPayException;

/**
 * Centraliza o tratamento dos erros que acontecem internamente em cada microsserviço. Esse tratamento é complementar ao
 * tratamento de erro do Zuul e dos Feign Clients, quando um microsserviço troca mensagens com os demais.
 *
 * @author fabio.tasco
 * @see ZuulErrorHandlerController
 */
@ControllerAdvice
public class ErrorHandlerController {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ErrorHandlerController.class);

	@ExceptionHandler(PimpoPayException.class)
	public ResponseEntity<Response<?>> handlePimpoPayException(final PimpoPayException e) {
		LOG.error(e.getMessage(), e);
		return ResponseEntity.ok(new Response<>(e.getError()));
	}

}
