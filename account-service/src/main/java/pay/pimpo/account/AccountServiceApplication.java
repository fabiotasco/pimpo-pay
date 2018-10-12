
package pay.pimpo.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
}
