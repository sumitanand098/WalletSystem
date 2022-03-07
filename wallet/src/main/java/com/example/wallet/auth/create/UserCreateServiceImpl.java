package com.example.wallet.auth.create;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.db.UserCreationDetail;
import com.example.wallet.db.dto.UserWalletEntity;

@Service
@Transactional
public class UserCreateServiceImpl implements UserCreateService {

	UserWalletEntity userWalletEntity;
	UserCreationDetail details;
	@Autowired UserDetails userDetails;

	@Override
	public String saveUserDetails( UserCreationDetail details ) {
		initializeValues( details );
		return populateUserDetails( details );
	}

	private String populateUserDetails( UserCreationDetail details ) {
		return userDetails.saveUserDetails( details, userWalletEntity );
	}

	private void initializeValues( UserCreationDetail details ) {
		this.details = details;
		userWalletEntity = new UserWalletEntity();

	}

}