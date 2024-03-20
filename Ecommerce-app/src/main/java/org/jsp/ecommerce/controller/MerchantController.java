package org.jsp.ecommerce.controller;

import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Merchant;
import org.jsp.ecommerce.service.MerchantService;
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
@RequestMapping("/merchants")
public class MerchantController {
	@Autowired
	private MerchantService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> save(@RequestBody Merchant merchant,HttpServletRequest request) {
		return service.save(merchant,request);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> update(@RequestBody Merchant merchant) {
		return service.update(merchant);
	}
	@DeleteMapping( "/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> delete(@PathVariable int id) {
		return service.delete(id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> find(@PathVariable int id){
		return service.find(id);
	}
	@PostMapping("/verifybyphone")
	public ResponseEntity<ResponseStructure<Merchant>> verify(@RequestParam long phone, @RequestParam String password){
		return service.verify(phone, password);
	}
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<Merchant>> verify(@RequestParam String email, @RequestParam String password){
		return service.verify(email, password);
	}
	@GetMapping("/verify-merchant")
	public  ResponseEntity<ResponseStructure<String>> activate(@RequestParam String tokken){
		return service.activate(tokken);
	}

	
	
	
}