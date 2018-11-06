
package pay.pimpo.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.pimpo.commons.entities.AccountPlan;
import pay.pimpo.commons.entities.PlanType;

public interface AccountPlanRepository extends JpaRepository<AccountPlan, Integer> {

	AccountPlan findByPlanType(PlanType planType);

}
