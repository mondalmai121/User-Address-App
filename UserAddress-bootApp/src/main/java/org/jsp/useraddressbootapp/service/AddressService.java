package org.jsp.useraddressbootapp.service;

import java.util.Optional;

import org.jsp.useraddressbootapp.dao.AddressDao;
import org.jsp.useraddressbootapp.dao.UserDao;
import org.jsp.useraddressbootapp.dto.Address;
import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.exception.IdNotFoundException;
import org.jsp.useraddressbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

	@Autowired
	private AddressDao adao;
	
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<Address>> addAddress(Address a, int user_id) {
		Optional<User> user=udao.findById(user_id);
		ResponseStructure<Address> structure=new ResponseStructure<>();
		if(user.isPresent()) {
			user.get().getAddress().add(a);
			a.setUser(user.get());
			structure.setMessage("Address added");
			structure.setData(adao.addAddress(a));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address a){
		Optional<Address> add=adao.findById(a.getId());
		ResponseStructure<Address> structure=new ResponseStructure<>();
		if(add.isPresent()) {
			Address addr=add.get();
			addr.setType(a.getType());
			addr.setBuilding_name(a.getBuilding_name());
			addr.setLandmark(a.getLandmark());
			addr.setArea(a.getArea());
			addr.setCity(a.getCity());
			addr.setState(a.getState());
			addr.setCountry(a.getCountry());
			addr.setPincode(a.getPincode());
			structure.setMessage("Address updated......");
			structure.setData(adao.updateAddress(addr));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Address>> findByUserId(int user_id){
		Optional<Address> add=adao.findByUserId(user_id);
		ResponseStructure<Address> structure=new ResponseStructure<>();
		if(add.isPresent()) {
			structure.setMessage("Address found...");
			structure.setData(add.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Address>> findByUserEmail(String email, String password){
		Optional<Address> add=adao.findByUser(email, password);
		ResponseStructure<Address> structure=new ResponseStructure<>();
		if(add.isPresent()) {
			structure.setMessage("Address found...");
			structure.setData(add.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Address>> findByCountry(String country){
		Optional<Address> add=adao.findByCountry(country);
		ResponseStructure<Address> structure=new ResponseStructure<>();
		if(add.isPresent()) {
			structure.setMessage("Address found...");
			structure.setData(add.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid email & password");
	}
}
