package rental.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rental.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
