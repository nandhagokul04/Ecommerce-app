package org.jsp.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.model.Orders;
import org.jsp.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDao {
	@Autowired
	private OrderRepository repository;
	
	public Orders save(Orders order) {
		return repository.save(order);
	}
	
	public Optional<List<Orders>> findbyuserid(int id) {
		return repository.findByUserId(id);
	}
	public void delete(int id) {
		 repository.deleteById(id);
	}
}
