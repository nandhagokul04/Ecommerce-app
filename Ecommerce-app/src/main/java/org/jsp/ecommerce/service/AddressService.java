package org.jsp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerce.Exception.IdNotFountException;
import org.jsp.ecommerce.Exception.InvalidCredentialsException;
import org.jsp.ecommerce.dao.AddressDao;
import org.jsp.ecommerce.dao.UserDao;
import org.jsp.ecommerce.dto.ResponseStructure;
import org.jsp.ecommerce.model.Address;
import org.jsp.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AddressService {
	@Autowired
	private AddressDao dao;
	@Autowired
	private UserDao udao;
	public ResponseEntity<ResponseStructure<Address>> save(Address address,@PathVariable int  user_id){
		Optional<User>user=udao.find(user_id);
		if(user.isPresent()) {
			User u=user.get();
			u.getAddress().add(address);
			address.setUser(u);
			ResponseStructure<Address> str=new ResponseStructure<>();
			str.setData(dao.save(address));
			str.setMessage("Address added");
			str.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(str,HttpStatus.CREATED);
		}
		throw new InvalidCredentialsException("address save failed");	
	}
	public ResponseEntity<ResponseStructure<Address>> update(Address address){
		Optional<Address>addres=dao.find(address.getId());
		if(addres.isPresent()) {
			 Address ad=addres.get();
			ad.setArea(address.getArea());
			ad.setBuildingname(address.getBuildingname());
			ad.setCity(address.getCity());
			ad.setCountry(address.getCountry());
			ad.setLandmark(address.getLandmark());
			ad.setPincode(address.getPincode());
			ad.setState(address.getState());
			ad.setType(address.getType());
			ResponseStructure<Address> str=new ResponseStructure<>();
			str.setData(dao.save(ad));
			str.setMessage("Address updated");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(str,HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Cannot update aAddress");	
	}
	public ResponseEntity<ResponseStructure<Address>> findbyid(int id){
		Optional<Address>addres=dao.find(id);
		if(addres.isPresent()) {
			ResponseStructure<Address> str=new ResponseStructure<>();
			str.setData(addres.get());
			str.setMessage("Address found");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(str,HttpStatus.OK);
		}
		throw new IdNotFountException("Invalid ID entered");	
	}
	public ResponseEntity<ResponseStructure<Address>> delete(int id){
		Optional<Address>addres=dao.find(id);
		if(addres.isPresent()) {
			dao.delete(id);
			ResponseStructure<Address> str=new ResponseStructure<>();
			str.setData(null);
			str.setMessage("Address deleted");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(str,HttpStatus.OK);
		}
		throw new IdNotFountException("Invalid ID entered");	
	}
	public ResponseEntity<ResponseStructure<List<Address>>> findbyuserid(int id){
	List<Address> addres= dao.findbyuser(id);
		if(addres.size()!=0) {
			ResponseStructure<List<Address>> str=new ResponseStructure<>();
			str.setData(addres);
			str.setMessage("user Address found");
			str.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Address>>>(str, HttpStatus.OK);
		}
		throw new IdNotFountException("Invalid ID entered");	
	}


}
