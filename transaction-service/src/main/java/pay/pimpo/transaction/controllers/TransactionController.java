
package pay.pimpo.transaction.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pay.pimpo.commons.api.Response;
import pay.pimpo.commons.clients.AuthClient;
import pay.pimpo.transaction.dto.TransactionDto;
import pay.pimpo.transaction.dto.TransactionResponseDto;
import pay.pimpo.transaction.rules.PurchaseRules;

@RestController
@ResponseStatus(HttpStatus.OK)
public class TransactionController {

	@Autowired
	private PurchaseRules purchaseRules;

	@PostMapping("/purchase")
	public Response<TransactionResponseDto> purchase(
		@RequestBody @Valid final TransactionDto transactionDto,
		@RequestHeader(AuthClient.USER_ID_HEADER_KEY) final Long userId)
		throws Exception {

		return purchaseRules.process(transactionDto, userId);
	}

}
