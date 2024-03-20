package org.jsp.ecommerce.service;

import java.util.Optional;

import org.jsp.ecommerce.Exception.IdNotFountException;
import org.jsp.ecommerce.Exception.ProductNotFoundException;
import org.jsp.ecommerce.dao.MerchantDao;
import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Merchant;
import org.jsp.ecommerce.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	@Autowired
	private ECommerceAppEmailService mail;

	public ResponseEntity<ResponseStructure<Merchant>> save(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		String message = mail.MailSender(merchant, request);
		structure.setMessage("Merchant saved !" + "," + message);
		structure.setData(dao.save(merchant));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> update(Merchant merchant) {
		Optional<Merchant> m = dao.find(merchant.getId());
		if (m.isPresent()) {
			Merchant p = m.get();
			p.setId(merchant.getId());
			p.setName(merchant.getName());
			p.setEmail(merchant.getEmail());
			p.setGst_number(merchant.getGst_number());
			p.setPassword(merchant.getPassword());
			p.setPhone(merchant.getPhone());
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant updated !");
			structure.setData(dao.save(p));
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<>(structure, HttpStatus.CREATED);
			}
			
		throw new IdNotFountException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<Merchant>> find(int id) {
		Optional<Merchant> m = dao.find(id);
		if (m.isPresent()) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant Found !");
			structure.setData(m.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<Merchant>> delete(int id) {
		Optional<Merchant> m = dao.find(id);
		if (m.isPresent()) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant deleted !");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.OK.value());
			dao.delete(id);
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verify(String email, String passwortd) {
		Optional<Merchant> m = dao.verify(email, passwortd);
		if (m.isPresent()) {
			Merchant p=m.get();
			if(p.getStatus().equals(AccountStatus.IN_ACTIVE.toString())) {
				throw new IllegalStateException("Account not activated");
			}
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant Found !");
			structure.setData(m.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verify(long phone, String passwortd) {
		Optional<Merchant> m = dao.verify(phone, passwortd);
		if (m.isPresent()) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			structure.setMessage("Merchant Found !");
			structure.setData(m.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid ID Entered");
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<Merchant> m = dao.findByToken(token);
		if (m.isPresent()) {
			Merchant merchant=m.get();
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
