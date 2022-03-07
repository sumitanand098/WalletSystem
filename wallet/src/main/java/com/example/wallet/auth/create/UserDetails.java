package com.example.wallet.auth.create;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.WalletUserRepository;
import com.example.wallet.common.Constants;
import com.example.wallet.db.UserCreationDetail;
import com.example.wallet.db.dto.UserWalletEntity;

@Service
@Transactional
public class UserDetails {

	@Autowired WalletUserRepository walletUserRepository;
	@Autowired PasswordEncoder passwordEncoder;

	public String saveUserDetails( UserCreationDetail details,
		UserWalletEntity userCreationDetail ) {
		try {
			userCreationDetail.setFirstName( details.getFirstName() );
			userCreationDetail.setLastName( details.getLastName() );
			userCreationDetail.setUsername( details.getUserName() );
			userCreationDetail.setUserPwd(
					passwordEncoder.encode( details.getPassword() ) );
			userCreationDetail.setIsActive( 1 );
			userCreationDetail.setEmail( details.getEmail() );
			walletUserRepository.save( userCreationDetail ).getId();
			return Constants.ResponseConstants.SUCCESS;
		}
		catch ( Exception e ) {
			// TODO: handle exception
			e.printStackTrace();
			return Constants.ResponseConstants.FAILURE;
		}
	}

}
