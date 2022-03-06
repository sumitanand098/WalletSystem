package com.example.wallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/wallet" )
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class WalletController {

	
	@GetMapping( path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseStatus(HttpStatus.CREATED)
	public String saveProfileDetails() {
		System.out.println( "FiELDS" );
		return "DATA VIEW RETURN";
	}
	
	
}
