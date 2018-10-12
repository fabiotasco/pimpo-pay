
package pay.pimpo.register.configurations;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "api";

	// private static final String CREATE_SCOPE = "#oauth2.hasScope('create')";
	// private static final String READ_SCOPE = "#oauth2.hasScope('read')";
	// private static final String UPDATE_SCOPE = "#oauth2.hasScope('update')";
	// private static final String DELETE_SCOPE = "#oauth2.hasScope('delete')";
	//
	// private static final String PATTERN = "/api/register/**";

	@Autowired
	private DataSource dataSource;

	@Value("${auth-service.url}")
	private String authUrl;

	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID).tokenStore(tokenStore());
	}

	// @Override
	// public void configure(final HttpSecurity http) throws Exception {
	// http.requestMatchers()
	// .antMatchers(PATTERN)
	// .and()
	// .authorizeRequests()
	// .antMatchers(HttpMethod.POST, PATTERN)
	// .access(CREATE_SCOPE)
	// .antMatchers(HttpMethod.PUT, PATTERN)
	// .access(UPDATE_SCOPE)
	// .antMatchers(HttpMethod.DELETE, PATTERN)
	// .access(DELETE_SCOPE)
	// .anyRequest()
	// .access(READ_SCOPE);
	// }

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.permitAll()
			.and()
			.cors()
			.disable()
			.csrf()
			.disable()
			.httpBasic()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(
				(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
			.accessDeniedHandler(
				(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED));
	}

	@Bean
	public ResourceServerTokenServices tokenService() {
		final RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setClientId("mobile");
		tokenServices.setClientSecret("7b17c7ed7ba3076f");
		tokenServices.setCheckTokenEndpointUrl(authUrl);

		return tokenServices;
	}

}
