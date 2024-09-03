package my.com.etiqa.maybank.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.com.etiqa.maybank.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select r from Product r where r.productName = :productName")
	public Optional<Product> findByProductName(String productName);

	// public Roles findById(Long id);
}
