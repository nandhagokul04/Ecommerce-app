package org.jsp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.Exception.IdNotFountException;
import org.jsp.ecommerce.Exception.ProductNotFoundException;
import org.jsp.ecommerce.dao.MerchantDao;
import org.jsp.ecommerce.dao.ProductDao;
import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Merchant;
import org.jsp.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao mdao;

	public ResponseEntity<ResponseStructure<Product>> save(Product product,int id) {
		Optional<Merchant> opmerchant=mdao.find(id);
		if(opmerchant.isPresent()) {
			Merchant m=opmerchant.get();
			m.getProducts().add(product);
			product.setMerchant(m);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setMessage("product added !");
		structure.setData(dao.save(product));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFountException("Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStructure<Product>> update(Product product) {
		Optional<Product> pro = dao.find(product.getId());
		if (pro.isPresent()) {
		Product p=pro.get();
		p.setId(product.getId());
		p.setBrand(product.getBrand());
		p.setCatagory(product.getCatagory());
		p.setDescription(product.getDescription());
		p.setName(product.getName());
		p.setCost(product.getCost());
		p.setImage_url(product.getImage_url());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setMessage("product updated !");
		structure.setData(dao.save(p));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFountException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<Product>> find(int id) {
		Optional<Product> m = dao.find(id);
		if (m.isPresent()) {
			ResponseStructure<Product> structure = new ResponseStructure<>();
			structure.setMessage("Product Found !");
			structure.setData(m.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		Optional<Product> m = dao.find(id);
		if (m.isPresent()) {
			dao.delete(id);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Product deleted !");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findbymerchantid(int id) {
		Optional<List<Product>> pro = dao.findbymerchantid(id);
		if (pro.isPresent()) {
			ResponseStructure<List<Product>> structure = new ResponseStructure<>();
			structure.setMessage("Product found !");
			structure.setData(pro.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid merchant ID Entered to find product");
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findall() {
			ResponseStructure<List<Product>> structure = new ResponseStructure<>();
			structure.setMessage("All Products found !");
			structure.setData(dao.findall());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findbybrand(String brand) {
		Optional<List<Product>>pro = dao.findbybrand(brand);
		if (pro.isPresent()) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setMessage("All Products found !");
		structure.setData(pro.get());
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		else {
			throw new IdNotFountException("No such Brand");
		}
	}
		public ResponseEntity<ResponseStructure<List<Product>>> findbycat(String cat) {
			Optional<List<Product>>pro = dao.findbycatagory(cat);
			if (pro.isPresent()) {
			ResponseStructure<List<Product>> structure = new ResponseStructure<>();
			structure.setMessage("All Products found !");
			structure.setData(pro.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
			}
			else {
				throw new IdNotFountException("No such catagory found");
			}
}


}
