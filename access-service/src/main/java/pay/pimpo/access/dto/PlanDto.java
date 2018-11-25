
package pay.pimpo.access.dto;

import pay.pimpo.commons.entities.PlanType;

public class PlanDto {

	private final PlanType planType;

	public PlanDto(final PlanType name) {
		planType = name;
	}

	public PlanType getName() {
		return planType;
	}

	@Override
	public String toString() {
		return planType.name();
	}

}
