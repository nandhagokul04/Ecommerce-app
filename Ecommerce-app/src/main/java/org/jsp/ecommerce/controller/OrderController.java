package org.jsp.ecommerce.controller;

import java.util.List;

import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Orders;
import org.jsp.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Orders>> save (@RequestParam int product_id, @RequestParam int user_id ){
		return service.save(product_id,user_id);
	}
	@GetMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<List<Orders>>> find  (@PathVariable int user_id ){
		return service.find(user_id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> delete (@PathVariable int id ){
		return service.delete(id);
	}

}
