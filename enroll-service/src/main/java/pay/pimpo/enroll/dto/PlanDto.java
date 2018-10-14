
package pay.pimpo.enroll.dto;

public class PlanDto {

	private final String name;

	public PlanDto(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
