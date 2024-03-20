package org.jsp.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.model.Product;
import org.jsp.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;

	public Product save(Product product) {
		return repository.save(product);
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

	public Optional<Product> find(int id) {
		return repository.findById(id);
	}
	public Optional<List<Product>> findbymerchantid(int id) {
		return repository.findbyMerchantId(id);
	}
	public List<Product> findall() {
		return repository.findAll();
	}
	public Optional<List<Product>> findbybrand(String brand) {
		return repository.findByBrand(brand);
	}
	public Optional<List<Product>> findbycatagory(String catagory) {
		return repository.findByCatagory(catagory);
	}
}
