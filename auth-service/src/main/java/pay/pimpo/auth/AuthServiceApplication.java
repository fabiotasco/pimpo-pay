
package pay.pimpo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
