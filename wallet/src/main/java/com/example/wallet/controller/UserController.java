package com.example.wallet.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/user" )
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class UserController {

//	int updateId;
//	@Autowired UserListingService userListingService;
//	@Autowired UserCreateService userCreateService;
//	@Autowired UserDeleteService userDeleteService;
//	@Autowired UserSignInService userSignInService;
//	@Autowired UserOldPasswordService userOldPasswordService;
//	@Autowired UserUpdateService userUpdateService;
//	@Autowired UserPasswordUpdateService userPasswordUpdateService;
//	@Autowired PolymathUserRepository polymathUserRepository;
//	@Autowired private JwtUtil jwtUtil;
//
//	@Autowired private AuthenticationManager authenticationManager;
//
//	@PostMapping( path = "/listing", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public List<UserPolymath> sendList() {
//		return userListingService.listPolymathUser();
//	}
//
//	@PostMapping( path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public Status saveUser( @RequestBody RequestedUserDetails details ) {
//		List<UserPolymath> users = polymathUserRepository.findAll();
//		for ( UserPolymath user : users ) {
//			if ( user.getEmail().equalsIgnoreCase( details.getEmail() )
//					&& user.getIsActive() == 1 ) {
//				return Status.EMAIL_ALREADY_EXISTS;
//			}
//			if ( user.getUsername().equalsIgnoreCase( details.getUserName() )
//					&& user.getIsActive() == 1 ) {
//				return Status.USER_NAME_ALREADY_EXISTS;
//			}
//			if ( user.getEmpId().equalsIgnoreCase( details.getEmpId() )
//					&& user.getIsActive() == 1 ) {
//				return Status.EMP_ID_ALREADY_EXISTS;
//			}
//		}
//		userCreateService.saveUserDetails( details );
//		return Status.SUCCESS;
//	}
//
//	@PostMapping( path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public Status UpdateUserDetails( @RequestBody RequestedUserUpdateDetails details ) {
//		this.updateId = details.getId();
//		UserPolymath userPolymath = new UserPolymath();
//		userPolymath = polymathUserRepository.findById( updateId ).get();
//		List<UserPolymath> users = polymathUserRepository.findAll();
//		for ( UserPolymath user : users ) {
//			if ( user.getEmail().equalsIgnoreCase( details.getEmail() )
//					&& !userPolymath.getEmail().equals( details.getEmail() )
//					&& user.getIsActive() == 1 ) {
//				return Status.EMAIL_ALREADY_EXISTS;
//			}
//			if ( user.getUsername().equalsIgnoreCase( details.getUserName() )
//					&& !userPolymath.getUsername().equals( details.getUserName() )
//					&& user.getIsActive() == 1 ) {
//				return Status.USER_NAME_ALREADY_EXISTS;
//			}
//		}
//		userUpdateService.saveUserUpdateDetails( details, updateId );
//		return Status.SUCCESS;
//	}
//
//	@PostMapping( path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public void deleteUsers( @RequestBody int[] ids ) {
//		userDeleteService.deleteuser( ids );
//	}
//
//	@PostMapping( path = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public SignInDetail signInUser( @RequestBody SignInDetails details ) throws Exception {
//		SignInDetail signInDetail = new SignInDetail();
//		Authentication authentication;
//		try {
//			authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken( details.getUserName(),
//						details.getPassword() ) );
//			//			signInDetail.setMatch( true );
//		}
//		catch ( Exception e ) {
//			signInDetail.setMatch( false );
//			throw new Exception( "Invalid username and password" );
//		}
//		signInDetail = userSignInService.checkPassword( details );
//		signInDetail.setAccessToken( jwtUtil.generateAccessToken( authentication ) );
//		//		signInDetail.setMatch( true );
//		return signInDetail;
//
//	}
//
//	@PostMapping( path = "/oldPwdChange", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public String OldPasswordCheck( @RequestBody RequestedUserPasswordUpdateDetails details,
//		Principal principal ) {
//		int principalId = getLoggedInUser( principal ).getId();
//		int requestid = details.getId();
//		UserPolymath userPolymath = new UserPolymath();
//		userPolymath = polymathUserRepository.findById( requestid ).get();
//		if ( ( requestid == principalId ) || ( userPolymath.getCreatedBy() == principalId
//				&& userPolymath.getRole() == 2 ) ) {
//			return userOldPasswordService.checkOldPassword( details );
//		}
//		else {
//			throw new ForbiddenException();
//		}
//	}
//
//	@PostMapping( path = "/pwdChange", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//
//	public void UpdateUserPasswordDetails(
//		@RequestBody RequestedUserPasswordUpdateDetails details,
//		Principal principal ) {
//
//		int principalId = getLoggedInUser( principal ).getId();
//		this.updateId = details.getId();
//		UserPolymath userPolymath = new UserPolymath();
//		userPolymath = polymathUserRepository.findById( updateId ).get();
//		if ( ( updateId == principalId ) || ( userPolymath.getCreatedBy() == principalId
//				&& userPolymath.getRole() == 2 ) ) {
//			userPasswordUpdateService.saveUserPasswordUpdateDetails( details, updateId );
//		}
//		else
//			throw new ForbiddenException();
//	}
//
//	@PostMapping( path = "/403", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE )
//	public String error403() {
//		return "/403";
//	}
//
//	public enum Status {
//		SUCCESS, EMAIL_ALREADY_EXISTS, EMP_ID_ALREADY_EXISTS, USER_NAME_ALREADY_EXISTS, FAILURE
//	}
//
//	private UserPolymath getLoggedInUser( Principal principal ) {
//		return polymathUserRepository.findByUsername( principal.getName() );
//	}
//
//	@SuppressWarnings( "serial" )
//	@ResponseStatus( HttpStatus.FORBIDDEN )
//	public class ForbiddenException extends RuntimeException {
//
//	}
}
