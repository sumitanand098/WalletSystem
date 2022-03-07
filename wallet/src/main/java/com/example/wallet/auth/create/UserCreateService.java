package com.example.wallet.auth.create;

import com.example.wallet.db.UserCreationDetail;

public interface UserCreateService {

	String saveUserDetails( UserCreationDetail details );

}