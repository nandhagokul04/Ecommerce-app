package org.jsp.ecommerce.dao;

import java.util.Optional;

import org.jsp.ecommerce.model.Address;
import org.jsp.ecommerce.repository.AdderssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AdderssRepository repository;

	public Address save(Address address) {
		return repository.save(address);
	}

	public Optional<Address> delete(int id) {
		repository.deleteById(id);
		return null ;
	}

	public Optional<Address> find(int id) {
		return repository.findById(id);
	}
	public Optional<Address> findbyuser(int id) {
		return repository.findById(id);
	}
	


}
