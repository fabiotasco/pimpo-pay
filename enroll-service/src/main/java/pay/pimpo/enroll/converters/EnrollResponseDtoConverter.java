
package pay.pimpo.enroll.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pay.pimpo.commons.dto.PhoneDto;
import pay.pimpo.commons.entities.Account;
import pay.pimpo.enroll.dto.EnrollResponseDto;
import pay.pimpo.enroll.dto.PlanDto;

@Component
public class EnrollResponseDtoConverter {

	public EnrollResponseDto convert(final Account account) {
		final List<PlanDto> plans = new ArrayList<>(account.getPlans().size());
		account.getPlans().forEach(plan -> plans.add(new PlanDto(plan.getName())));

		final List<PhoneDto> phones = new ArrayList<>(account.getNumbers().size());
		account.getNumbers().forEach(accountNumber -> {
			phones.add(new PhoneDto(
				accountNumber.getNumber(),
				accountNumber.getOperator().getName(),
				accountNumber.getStatus().getName()));
		});

		return new EnrollResponseDto(
			account.getHash(),
			account.getType().getName(),
			account.getStatus().getName(),
			plans,
			phones);
	}

}
