package org.jsp.useraddressbootapp.service;

import java.util.Optional;

import org.jsp.useraddressbootapp.dao.UserDao;
import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.exception.IdNotFoundException;
import org.jsp.useraddressbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User u) {
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setMessage("user saved successful");
		structure.setData(udao.saveUser(u));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User u){
		Optional<User> user=udao.findById(u.getId());
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(user.isPresent()) {
			User users=user.get();
			users.setName(u.getName());
			users.setPhone(u.getPhone());
			users.setEmail(u.getEmail());
			users.setGender(u.getGender());
			users.setPassword(u.getPassword());
			structure.setMessage("user saved successful");
			structure.setData(udao.saveUser(u));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		Optional<User> user=udao.findById(id);
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(user.isPresent()) {
			structure.setMessage("User found.....");
			structure.setData(user.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyByPhone(long phone, String password){
		Optional<User> user=udao.verify(phone, password);
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(user.isPresent()) {
			structure.setMessage("User found.....");
			structure.setData(user.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Entered invalid phone & password");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyByEmail(String email, String password){
		Optional<User> user=udao.verify(email, password);
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(user.isPresent()) {
			structure.setMessage("User found.....");
			structure.setData(user.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Entered invalid phone & password");
	}
}
