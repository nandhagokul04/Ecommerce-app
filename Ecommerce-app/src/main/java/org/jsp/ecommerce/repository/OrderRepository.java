package org.jsp.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query("select m from Orders m where m.order_user_id=?1")
	public Optional<List<Orders>> findByUserId(int cart_user_id);

	
}
