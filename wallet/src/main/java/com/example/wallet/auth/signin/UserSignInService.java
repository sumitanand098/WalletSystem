package com.example.wallet.auth.signin;

import com.example.wallet.db.UserCreationDetail;
import com.example.wallet.db.SignInDetails;

public interface UserSignInService {

	UserCreationDetail checkPassword( SignInDetails details );

}