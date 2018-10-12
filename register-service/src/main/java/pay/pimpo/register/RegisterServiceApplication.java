
package pay.pimpo.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RegisterServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(RegisterServiceApplication.class, args);
	}
}
