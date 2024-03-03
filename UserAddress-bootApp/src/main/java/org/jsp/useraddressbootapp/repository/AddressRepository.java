package org.jsp.useraddressbootapp.repository;

import java.util.Optional;

import org.jsp.useraddressbootapp.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	@Query("select u.address from User u where u.id=?1")
	public Optional<Address> verify(int user_id);
	
	@Query("select u.address from User u where u.email=?1 and u.password=?2")
	public Optional<Address> verify(String email, String password);
	
	@Query("select a from Address a where a.country=?1")
	public Optional<Address> verify(String country);
}
