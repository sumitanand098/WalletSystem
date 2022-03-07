package com.example.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet.auth.create.UserCreateService;
import com.example.wallet.auth.security.JwtUtil;
import com.example.wallet.auth.signin.UserSignInService;
import com.example.wallet.common.Constants;
import com.example.wallet.common.WalletResponse;
import com.example.wallet.common.WalletResponseWrapper;
import com.example.wallet.db.SignInDetails;
import com.example.wallet.db.UserCreationDetail;

@RestController
@RequestMapping( value = "/user" )
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class UserController {

	@Autowired UserCreateService userCreateService;
	@Autowired UserSignInService userSignInService;
	@Autowired private JwtUtil jwtUtil;
	@Autowired private AuthenticationManager authenticationManager;

	@PostMapping( path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse>
			debitTransactionDetails( @RequestBody UserCreationDetail userCreationDetail ) {
		WalletResponseWrapper walletResponseWrapper = new WalletResponseWrapper();
		WalletResponse walletResponse = walletResponseWrapper.createResponse(
				Constants.ResponseConstants.SUCCESS,
				userCreateService.saveUserDetails( userCreationDetail ) );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );
	}

	@PostMapping( path = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<WalletResponse> signInUser( @RequestBody SignInDetails details )
			throws Exception {
		UserCreationDetail userCreationDetail = new UserCreationDetail();
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken( details.getUserName(),
						details.getPassword() ) );
		}
		catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception( "Invalid username and password" );
		}
		userCreationDetail = userSignInService.checkPassword( details );
		userCreationDetail.setAccessToken( jwtUtil.generateAccessToken( authentication ) );
		WalletResponseWrapper walletResponseWrapper = new WalletResponseWrapper();
		WalletResponse walletResponse = walletResponseWrapper.createResponse(
				Constants.ResponseConstants.SUCCESS,
				userCreationDetail );
		return new ResponseEntity<WalletResponse>( walletResponse, HttpStatus.OK );

	}

}
