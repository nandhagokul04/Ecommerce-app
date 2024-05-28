package org.jsp.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<ProductCart, Integer> {

	@Query("select m from ProductCart m where m.cart_user_id=?1")
	public Optional<List<ProductCart>> findByUserId(int cart_user_id);
}
