package org.jsp.useraddressbootapp.controller;

import org.jsp.useraddressbootapp.dto.Address;
import org.jsp.useraddressbootapp.dto.ResponseStructure;
import org.jsp.useraddressbootapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponseStructure<Address>> addAddress(@RequestBody Address a,
			@PathVariable(name = "id") int user_id) {
		return service.addAddress(a, user_id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address a) {
		return service.updateAddress(a);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> findByUser(@PathVariable(name = "id") int user_id) {
		return service.findByUserId(user_id);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Address>> findByUserEmail(@RequestParam String email,
			@RequestParam String password) {
		return service.findByUserEmail(email, password);
	}
	
	@GetMapping("/find/{country}")
	public ResponseEntity<ResponseStructure<Address>> findByCountry(@PathVariable(name="country") String country){
		return service.findByCountry(country);
	}
}
