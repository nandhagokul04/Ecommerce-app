package org.jsp.ecommerce.dao;

import java.util.Optional;

import org.jsp.ecommerce.model.User;
import org.jsp.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User save(User user) {
		return repository.save(user);
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

	public Optional<User> find(int id) {
		return repository.findById(id);
	}
	public Optional<User> verify(long phone,String password) {
		System.err.println("verify  dao");
		return repository.verify(phone, password);
	}
	public Optional<User> verify(String phone,String password) {
		return repository.verify(phone, password);
	}
	public Optional<User> findByToken(String token) {
		return repository.findByToken(token);
	}
	
}
