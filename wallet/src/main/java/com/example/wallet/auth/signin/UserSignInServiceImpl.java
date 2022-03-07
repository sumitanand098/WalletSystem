package com.example.wallet.auth.signin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.WalletUserRepository;
import com.example.wallet.common.Constants;
import com.example.wallet.db.SignInDetails;
import com.example.wallet.db.UserCreationDetail;
import com.example.wallet.db.dto.UserWalletEntity;

@Service
public class UserSignInServiceImpl implements UserSignInService, UserDetailsService {

	@Autowired WalletUserRepository walletUserRepository;
	@Autowired PasswordEncoder passwordEncoder;

	@Override
	public UserCreationDetail checkPassword( SignInDetails details ) {
		UserCreationDetail signInDetail = new UserCreationDetail();
		UserWalletEntity userWallet =
				walletUserRepository.findByUsername( details.getUserName() );
		signInDetail.setUserName( userWallet.getUsername() );
		signInDetail.setPassword( userWallet.getUserPwd() );
		signInDetail.setUserId( userWallet.getId() );
		signInDetail.setFirstName( userWallet.getFirstName() );

		return signInDetail;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails
			loadUserByUsername( String username ) throws UsernameNotFoundException {

		UserWalletEntity user = walletUserRepository.findByUsername( username );

		if ( user == null ) {
			throw new UsernameNotFoundException( username );
		}

		return new org.springframework.security.core.userdetails.User( user.getUsername(),
			user.getUserPwd(), getAuthorities( user ) );

	}

	private Collection< ? extends GrantedAuthority> getAuthorities( UserWalletEntity user ) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add( new SimpleGrantedAuthority( Constants.Role ) );
		return authorities;
	}
}
