
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.NetworkOperator;

public interface NetworkOperatorRepository extends JpaRepository<NetworkOperator, Integer> {

	NetworkOperator findByName(String name);

}
