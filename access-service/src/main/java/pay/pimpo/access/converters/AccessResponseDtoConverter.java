
package pay.pimpo.access.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pay.pimpo.access.dto.ContractDto;
import pay.pimpo.access.dto.AccessResponseDto;
import pay.pimpo.access.dto.PlanDto;
import pay.pimpo.commons.dto.PhoneDto;
import pay.pimpo.commons.entities.Account;

@Component
public class AccessResponseDtoConverter {

	public AccessResponseDto convert(final Account account) {
		final List<ContractDto> contracts = new ArrayList<>(account.getContracts().size());
		account.getContracts().forEach(contract -> contracts.add(new ContractDto(contract.getContractType())));

		final List<PlanDto> plans = new ArrayList<>(account.getPlans().size());
		account.getPlans().forEach(plan -> plans.add(new PlanDto(plan.getPlanType())));

		final List<PhoneDto> phones = new ArrayList<>(account.getNumbers().size());
		account.getNumbers().forEach(accountNumber -> {
			phones.add(new PhoneDto(
				accountNumber.getNumber(),
				accountNumber.getNetworkOperator().getName(),
				accountNumber.getStatus()));
		});

		return new AccessResponseDto(account.getHash(), contracts, plans, phones, account.getStatus());
	}

}
