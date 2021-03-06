
package pay.pimpo.transaction.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AuthClient;
import pay.pimpo.commons.entities.TransactionEvent;
import pay.pimpo.commons.exceptions.AccountNotEnrolledOnPlanException;
import pay.pimpo.commons.exceptions.TransactionNotFoundException;
import pay.pimpo.transaction.dto.DepositDto;
import pay.pimpo.transaction.dto.PurchaseDto;
import pay.pimpo.transaction.dto.StatementDto;
import pay.pimpo.transaction.dto.TransactionDetailsDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.dto.TransferDto;
import pay.pimpo.transaction.rules.CancelRules;
import pay.pimpo.transaction.rules.DepositRules;
import pay.pimpo.transaction.rules.PurchaseRules;
import pay.pimpo.transaction.rules.StatementRules;
import pay.pimpo.transaction.rules.TransactionDetailsRules;
import pay.pimpo.transaction.rules.TransactionEventRules;
import pay.pimpo.transaction.rules.TransferRules;

@RestController
@ResponseStatus(HttpStatus.OK)
class TransactionController {

	@Autowired
	private PurchaseRules purchaseRules;

	@Autowired
	private DepositRules depositRules;

	@Autowired
	private StatementRules statementRules;

	@Autowired
	private TransferRules transferRules;

	@Autowired
	private TransactionEventRules transactionEventRules;

	@Autowired
	private TransactionDetailsRules transactionDetailsRules;

	@Autowired
	private CancelRules cancelRules;

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

	@PostMapping("/transfer")
	Response<TransactionResponseDto> transfer(
		@RequestBody @Valid final TransferDto transferDto,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws Exception {

		return transferRules.process(transferDto, userId);
	}

	@DeleteMapping("/{transactionId}/cancel")
	Response<TransactionResponseDto> cancel(
		@PathVariable("transactionId") final Long transactionId,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws Exception {

		return cancelRules.process(transactionId, userId);
	}

	/** Extrato bancário */
	@GetMapping("/statement")
	Response<StatementDto> statement(
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId,
		@RequestParam("page") final int page,
		@RequestParam("size") final int size) {

		return statementRules.process(userId, PageRequest.of(page, size));
	}

	@PostMapping("/events")
	Response<Void> addEvent(@RequestBody final TransactionEvent transactionEvent) throws TransactionNotFoundException {
		return new Response<>(transactionEventRules.addEvent(transactionEvent));
	}

	@GetMapping("/{transactionId}")
	Response<TransactionDetailsDto> getTransationDetails(
		@PathVariable("transactionId") final Long transactionId,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws TransactionNotFoundException {

		return transactionDetailsRules.process(transactionId, userId);
	}

}
