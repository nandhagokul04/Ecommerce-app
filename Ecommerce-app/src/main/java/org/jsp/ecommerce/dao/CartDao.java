package org.jsp.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.model.ProductCart;
import org.jsp.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {
	@Autowired
	private CartRepository repository;
	
	public ProductCart save(ProductCart cart) {
		return repository.save(cart);
	}
	public Optional<List<ProductCart>> findbyuserid(int id) {
		return repository.findByUserId(id);
	}
	public void delete(int id) {
		repository.deleteById(id);
	}

}
