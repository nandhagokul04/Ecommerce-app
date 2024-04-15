package org.jsp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.Exception.IdNotFountException;
import org.jsp.ecommerce.Exception.InvalidCredentialsException;
import org.jsp.ecommerce.dao.CartDao;
import org.jsp.ecommerce.dao.ProductDao;
import org.jsp.ecommerce.dao.UserDao;
import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Product;
import org.jsp.ecommerce.model.ProductCart;
import org.jsp.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	@Autowired
	private CartDao dao;
	@Autowired
	private UserDao udao;
	@Autowired
	private ProductDao pdao;

	public ResponseEntity<ResponseStructure<ProductCart>> save(int product_id, int id) {
		Optional<Product> product = pdao.find(product_id);
		Optional<User> user = udao.find(id);
		if (user.isPresent() && product.isPresent()) {
			Product p = product.get();
			ProductCart c = new ProductCart();
			c.getProducts().add(p);
			c.setCart_user_id(id);
			ResponseStructure<ProductCart> str = new ResponseStructure<>();
			str.setData(dao.save(c));
			str.setMessage("Added to cart");
			str.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<ProductCart>>(str, HttpStatus.CREATED);
		}
		throw new IdNotFountException(null);
	}

	public ResponseEntity<ResponseStructure<List<ProductCart>>> find(int id) {
		Optional<List<ProductCart>> cart = dao.findbyuserid(id);
		if (cart.isEmpty()) {
			throw new InvalidCredentialsException("not found");
		} else {
			ResponseStructure<List<ProductCart>> str = new ResponseStructure<>();
			str.setData(cart.get());
			str.setMessage("found");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<ProductCart>>>(str, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<ProductCart>>> deletebyproductid(int id) {
		ResponseStructure<List<ProductCart>> str = new ResponseStructure<>();
		dao.delete(id);
		str.setMessage("found");
		str.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<ProductCart>>>(str, HttpStatus.OK);
	}
}
