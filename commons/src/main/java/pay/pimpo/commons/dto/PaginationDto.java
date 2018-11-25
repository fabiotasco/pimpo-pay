
package pay.pimpo.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PaginationDto {

	private final Integer page;
	private final Integer size;
	private final Integer totalPages;
	private final Long totalElements;

	public PaginationDto(final Integer page, final Integer size, final Integer totalPages, final Long totalElements) {
		this.page = page;
		this.size = size;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	@Override
	public String toString() {
		return "PaginationDto [page=" + page
			+ ", size="
			+ size
			+ ", totalPages="
			+ totalPages
			+ ", totalElements="
			+ totalElements
			+ "]";
	}

}
