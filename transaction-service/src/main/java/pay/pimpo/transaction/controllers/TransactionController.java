
package pay.pimpo.transaction.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AuthClient;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.exceptions.TransactionNotFoundException;
import pay.pimpo.configurations.HazelcastConfiguration;
import pay.pimpo.transaction.dto.TransactionDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.rules.PurchaseRules;
import pay.pimpo.transaction.rules.TransactionRules;

@RestController
@ResponseStatus(HttpStatus.OK)
public class TransactionController {

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Autowired
	private PurchaseRules purchaseRules;

	@Autowired
	private TransactionRules transactionRules;

	@PostMapping("/purchase")
	public Response<TransactionResponseDto> purchase(
		@RequestBody @Valid final TransactionDto transactionDto,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws Exception {

		return purchaseRules.process(transactionDto, userId);
	}

	@PostMapping("/events")
	Response<Void> addEvent(@RequestBody final TransactionEvent transactionEvent) throws TransactionNotFoundException {
		return new Response<>(transactionRules.addEvent(transactionEvent));
	}

	@GetMapping("/queues")
	public Response<Integer> getQueues() {
		final IQueue<TransactionEvent> queue = hazelcastInstance.getQueue(HazelcastConfiguration.CLEARING_QUEUE_NAME);
		return new Response<>(queue.size());
	}

}
