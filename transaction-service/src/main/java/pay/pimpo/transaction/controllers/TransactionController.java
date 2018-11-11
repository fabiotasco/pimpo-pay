
package pay.pimpo.transaction.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AuthClient;
import pay.pimpo.commons.entities.Transaction;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.exceptions.AccountNotEnrolledOnPlanException;
import pay.pimpo.commons.exceptions.TransactionNotFoundException;
import pay.pimpo.transaction.dto.DepositDto;
import pay.pimpo.transaction.dto.PurchaseDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.rules.DepositRules;
import pay.pimpo.transaction.rules.PurchaseRules;
import pay.pimpo.transaction.rules.TransactionRules;

@RestController
@ResponseStatus(HttpStatus.OK)
class TransactionController {

	@Autowired
	private PurchaseRules purchaseRules;

	@Autowired
	private DepositRules depositRules;

	@Autowired
	private TransactionRules transactionRules;

	@PostMapping("/purchase")
	Response<TransactionResponseDto> purchase(
		@RequestBody @Valid final PurchaseDto transactionDto,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws Exception {

		return purchaseRules.process(transactionDto, userId);
	}

	@PostMapping("/deposit")
	Response<TransactionResponseDto> deposit(
		@RequestBody @Valid final DepositDto depositDto,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws AccountNotEnrolledOnPlanException {

		return depositRules.process(depositDto, userId);
	}

	@DeleteMapping("/transaction/{transactionId}/cancel")
	Response<TransactionResponseDto> cancel() {
		// TODO: Implementar!
		return null;
	}

	@GetMapping("/pan/{pan}/transactions")
	Response<Transaction> listTransactions() {
		// TODO: Implementar!
		return null;
	}

	@PostMapping("/events")
	Response<Void> addEvent(@RequestBody final TransactionEvent transactionEvent) throws TransactionNotFoundException {
		return new Response<>(transactionRules.addEvent(transactionEvent));
	}

}
