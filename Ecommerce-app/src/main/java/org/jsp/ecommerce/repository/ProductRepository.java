package org.jsp.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("select p from Product p  where p.merchant.id=?1")
	Optional<List<Product>> findbyMerchantId(int id);
	
	Optional<List<Product>> findByBrand(String brand);
	
	Optional<List<Product>> findByCatagory(String brand);
}
