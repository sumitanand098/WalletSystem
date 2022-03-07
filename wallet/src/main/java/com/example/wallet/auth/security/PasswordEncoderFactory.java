package com.example.wallet.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderFactory {

	@Bean
	public PasswordEncoder delegatingPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}