package org.jsp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.Exception.IdNotFountException;
import org.jsp.ecommerce.Exception.InvalidCredentialsException;
import org.jsp.ecommerce.dao.OrderDao;
import org.jsp.ecommerce.dao.ProductDao;
import org.jsp.ecommerce.dao.UserDao;
import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Orders;
import org.jsp.ecommerce.model.Product;
import org.jsp.ecommerce.model.User;
import org.jsp.ecommerce.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderDao dao;
	@Autowired
	private UserDao udao;
	@Autowired
	private ProductDao pdao;

	public ResponseEntity<ResponseStructure<Orders>> save(int product_id,int id) {
		Optional<Product>product=pdao.find(product_id);
		Optional<User>user=udao.find(id);
		if(user.isPresent()&& product.isPresent()) {
			Product p=product.get();
			Orders c=new Orders();
			c.setStatus(OrderStatus.PENDING.toString());
			c.getProducts().add(p);
			c.setOrder_user_id(id);	
		ResponseStructure<Orders> str=new ResponseStructure<>();
		str.setData(dao.save(c));
		str.setMessage("Added to cart");
		str.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Orders>>(str,HttpStatus.CREATED);
		}
		throw new IdNotFountException(null);
	}
	
	public ResponseEntity<ResponseStructure<List<Orders>>> find(int id) {
		Optional<List<Orders>> cart=dao.findbyuserid(id);
		if(cart.isEmpty()) {
			throw new InvalidCredentialsException("not found");	
	}
		else {
			ResponseStructure<List<Orders>> str=new ResponseStructure<>();
			str.setData( cart.get());
			str.setMessage("found");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Orders>>>(str,HttpStatus.OK );
		}
	}
	public ResponseEntity<ResponseStructure<String>>delete(int id) {
			ResponseStructure<String> str=new ResponseStructure<>();
			dao.delete(id);
			str.setMessage("found");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.OK );
		}
	

}
