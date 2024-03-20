package org.jsp.ecommerce.dao;

import java.util.Optional;

import org.jsp.ecommerce.model.Merchant;
import org.jsp.ecommerce.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository repository;

	public Merchant save(Merchant merchant) {
		return repository.save(merchant);
	}
	
	public void delete(int id) {
		 repository.deleteById(id);
	}
	
	public Optional<Merchant> find(int id) {
		return repository.findById(id);
	}
	
	public Optional<Merchant> verify(String email,String password) {
		return repository.verify(email,password);
	}
	
	public Optional<Merchant> verify(long phone,String password) {
		return repository.verify(phone,password);
	}
	
	public Optional<Merchant> findByToken(String token) {
		return repository.findByToken(token);
	}
	
	
	

}
