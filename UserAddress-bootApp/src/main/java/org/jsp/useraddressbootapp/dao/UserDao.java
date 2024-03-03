package org.jsp.useraddressbootapp.dao;

import java.util.Optional;

import org.jsp.useraddressbootapp.dto.User;
import org.jsp.useraddressbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;
	
	public User saveUser(User u) {
		return repository.save(u);
	}
	
	public User updateUser(User u) {
		return repository.save(u);
	}
	
	public Optional<User> findById(int id) {
		return repository.findById(id);
	}
	
	public Optional<User> verify(long phone, String password) {
		return repository.verify(phone, password);
	}
	
	public Optional<User> verify(String email, String password) {
		return repository.verify(email, password);
	}
	
}
