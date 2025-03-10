package org.jsp.ecommerce.controller;

import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.User;
import org.jsp.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> save(@RequestBody User user,HttpServletRequest req) {
		return service.save(user,req);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> update(@RequestBody User user) {
		return service.update(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> delete(@PathVariable int id) {
		return service.delete(id);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<User>> find(@PathVariable int id) {
		return service.find(id);
	}
	@PostMapping("/verifybyphone")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam long phone,@RequestParam String password) {
		return service.verify(phone, password);
	}
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam String email,@RequestParam String password) {
		System.err.println("user verify called ---------------------------------------------");
		return service.verify(email, password);
	}
	@GetMapping("/verify-user")
	public  ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token){
		return service.activate(token);
	}


}
