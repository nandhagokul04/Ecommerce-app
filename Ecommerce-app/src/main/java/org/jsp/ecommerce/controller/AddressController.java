package org.jsp.ecommerce.controller;

import java.util.List;

import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Address;
import org.jsp.ecommerce.service.AddressService;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService service;

	@PostMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<Address>> save(@RequestBody Address address,@PathVariable int user_id) {
		System.err.println("address save called");
		return service.save(address, user_id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> update(@RequestBody Address address) {
		return service.update(address);
	}
	@DeleteMapping( "/{id}")
	public ResponseEntity<ResponseStructure<Address>> delete(@PathVariable int id) {
		return service.delete(id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> find (@PathVariable int id){
		return service.findbyid(id);
	}
	@GetMapping("/user_id/{id}")
	public ResponseEntity<ResponseStructure<List<Address>>> findbyuser (@PathVariable int id){
		return service.findbyuserid(id);
	}
	


	
	
	
}
