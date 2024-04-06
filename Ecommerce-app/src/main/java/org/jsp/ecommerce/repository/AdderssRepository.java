package org.jsp.ecommerce.repository;

import java.util.List;


import org.jsp.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdderssRepository extends JpaRepository<Address, Integer> {
	
	@Query("select a from Address a where a.user.id=?1")
	public List<Address> findbyuser(int id);
	
	
}
