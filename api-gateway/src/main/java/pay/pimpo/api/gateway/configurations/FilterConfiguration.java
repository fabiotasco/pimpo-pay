
package pay.pimpo.api.gateway.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pay.pimpo.api.gateway.filters.AuthenticationRouterFilter;

@Configuration
public class FilterConfiguration {

	@Bean
	public AuthenticationRouterFilter authenticationFilter() {
		return new AuthenticationRouterFilter();
	}

}
