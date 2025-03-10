package org.jsp.ecommerce.service;

import java.util.Optional;

import org.jsp.ecommerce.Exception.IdNotFountException;
import org.jsp.ecommerce.Exception.ProductNotFoundException;
import org.jsp.ecommerce.dao.UserDao;
import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.User;
import org.jsp.ecommerce.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserEmailService mail;

	public ResponseEntity<ResponseStructure<User>> save(User user,HttpServletRequest request) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(45));
		String message = mail.MailSender(user, request);
		structure.setMessage("user saved !" + "," + message);
		structure.setData(dao.save(user));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> update(User user) {
		Optional<User> m = dao.find(user.getId());
		if (m.isPresent()) {
			User p=m.get();
			p.setId(user.getId());
			p.setName(user.getName());
			p.setEmail(user.getEmail());
			p.setPassword(user.getPassword());
			p.setPhone(user.getPhone());
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User updated !");
			structure.setData(dao.save(p));
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFountException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<User>> find(int id) {
		Optional<User> m = dao.find(id);
		if (m.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User Found !");
			structure.setData(m.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<User>> delete(int id) {
		Optional<User> m = dao.find(id);
		if (m.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User deleted !");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.OK.value());
			dao.delete(id);
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}
	public ResponseEntity<ResponseStructure<User>> verify(String email,String password) {
		Optional<User> m = dao.verify(email, password);
		if (m.isPresent()) {
			User p=m.get();
			if(p.getStatus().equals(AccountStatus.ACTIVE.toString())) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("User Found !");
			structure.setData(p);
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
			}
			throw new IllegalStateException("Account not activated");
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}
	public ResponseEntity<ResponseStructure<User>> verify(long phone,String password) {
		Optional<User> m = dao.verify(phone, password);
		if (m.isPresent()) {
				ResponseStructure<User> structure = new ResponseStructure<>();
				structure.setMessage("User Found !");
				structure.setData(m.get());
				structure.setStatuscode(HttpStatus.OK.value());
				return new ResponseEntity<>(structure, HttpStatus.OK);
		}
	
		throw new ProductNotFoundException("Invalid ID Entered");
	}
	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<User> m = dao.findByToken(token);
		if (m.isPresent()) {
			User merchant=m.get();
			merchant.setToken(null);
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			dao.save(merchant);
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setMessage("Merchant Found !");
			structure.setData("Account verified and activated");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	


}
