package org.jsp.useraddressbootapp.dao;

import java.util.Optional;

import org.jsp.useraddressbootapp.dto.Address;
import org.jsp.useraddressbootapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepository repository;
	
	public Address addAddress(Address a) {
		return repository.save(a);
	}
	
	public Address updateAddress(Address a) {
		return repository.save(a);
	}
	
	public Optional<Address> findById(int id) { //Optional 
		return repository.findById(id);
	}
	
	public Optional<Address> findByUserId(int user_id) {
		return repository.verify(user_id);
	}
	
	public Optional<Address> findByUser(String email, String password){
		return repository.verify(email, password);
	}
	
	public Optional<Address> findByCountry(String country) {
		return repository.verify(country);
	}
}
