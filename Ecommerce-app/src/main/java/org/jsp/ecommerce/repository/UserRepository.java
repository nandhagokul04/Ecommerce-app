package org.jsp.ecommerce.repository;

import java.util.Optional;

import org.jsp.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select m from User m where m.email=?1 and m.password=?2 ")
	public Optional<User> verify(String email,String password);
	
	@Query("select m from User m where m.phone=?1 and m.password=?2 ")
	public Optional<User> verify(long phone,String password);

	public Optional<User> findByToken(String token);

}
