
package pay.pimpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AccessServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AccessServiceApplication.class, args);
	}
}
