package com.example.wallet.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired private JwtFilter jwtFilter;
	@Autowired UserDetailsService userDetailsService;
	@Autowired PasswordEncoder passwordEncoder;

	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService( userDetailsService ).passwordEncoder( passwordEncoder );
	}

	@Bean( name = BeanIds.AUTHENTICATION_MANAGER )
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers( "/user/create", "/" ).permitAll()
				.antMatchers( "/user/sign-in", "/" ).permitAll()
				.anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedPage( "/user/403" ).and()
				.sessionManagement()
				.sessionCreationPolicy( SessionCreationPolicy.STATELESS );
		http.cors();
		http.addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class );

	}

}