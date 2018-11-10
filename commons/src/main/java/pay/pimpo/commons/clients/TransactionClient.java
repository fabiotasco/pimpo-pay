
package pay.pimpo.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.entities.TransactionEvent;

/**
 * @author fabio.tasco
 * @see TransactionController
 */
@FeignClient(name = "transaction-service", url = "${api.client.transaction-service.url}")
public interface TransactionClient {

	@PostMapping("/events")
	Response<Void> addEvent(@RequestBody final TransactionEvent build);

}
