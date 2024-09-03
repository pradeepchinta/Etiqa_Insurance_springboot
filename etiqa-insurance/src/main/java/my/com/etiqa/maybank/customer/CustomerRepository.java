package my.com.etiqa.maybank.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.etiqa.maybank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByEmail(String email);

}
