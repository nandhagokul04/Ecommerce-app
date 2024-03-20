package org.jsp.ecommerce.controller;

import java.util.List;

import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Product;
import org.jsp.ecommerce.service.ProductService;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> save(@PathVariable int id,@RequestBody Product product) {
		return service.save(product,id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> update(@RequestBody Product product) {
		return service.update(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> delete(@PathVariable int id) {
		return service.delete(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> find(@PathVariable int id) {
		return service.find(id);
	}
	
	@GetMapping("/merchant_id/{id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findbymerchantid(@PathVariable int id) {
		return service.findbymerchantid(id);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> findall() {
		return service.findall();
	}
	@GetMapping("/brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> bybrand(@PathVariable String brand) {
		return service.findbybrand(brand);
	}
	@GetMapping("/catagory/{catagory}")
	public ResponseEntity<ResponseStructure<List<Product>>> bycat(@PathVariable String catagory) {
		System.err.println("catagory");
		return service.findbycat(catagory);
	}
}
