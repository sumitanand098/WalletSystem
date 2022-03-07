package com.example.wallet.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.wallet.common.Constants.SecurityConstants;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired private JwtUtil jwtUtill;
	@Autowired UserDetailsService service;

	@Override
	protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain ) throws ServletException, IOException {
		String autherizationHeader = request.getHeader( SecurityConstants.HEADER_STRING );
		String token = null;
		String username = null;
		if ( autherizationHeader != null
				&& autherizationHeader.startsWith( SecurityConstants.TOKEN_PREFIX ) ) {
			token = autherizationHeader.replace( SecurityConstants.TOKEN_PREFIX, "" );
			try {
				username = jwtUtill.getUsernameFromToken( token );
			}
			catch ( IllegalArgumentException e ) {
				logger.error(
						"Illegeal Argumnet : Error occured while getting username from the token" );
			}
		}
		if ( username != null
				&& SecurityContextHolder.getContext().getAuthentication() == null ) {
			UserDetails userDetails = service.loadUserByUsername( username );
			if ( jwtUtill.validateToken( token, userDetails ) ) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						jwtUtill.getAuthentication( token,
								SecurityContextHolder.getContext().getAuthentication(),
								userDetails );
				usernamePasswordAuthenticationToken
						.setDetails(
								new WebAuthenticationDetailsSource().buildDetails( request ) );
				SecurityContextHolder.getContext()
						.setAuthentication( usernamePasswordAuthenticationToken );
				if ( jwtUtill.isTokenExpiredBeforeFiveMin( token ) ) {
					response.setHeader( "Pragma", jwtUtill
							.generateAccessToken( usernamePasswordAuthenticationToken ) );
				}
				else
					response.setHeader( "Pragma", "" );
			}
		}
		filterChain.doFilter( request, response );
	}

}
