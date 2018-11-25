
package pay.pimpo.commons.converters;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import pay.pimpo.commons.dto.PaginationDto;

@Component
public class PaginationDtoConverter {

	public PaginationDto convert(final Page<?> page) {
		return new PaginationDto(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
	}

}
