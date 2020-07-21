package com.hk.board.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private boolean postOnly;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("--AuthenticationFilter(UsernamePasswordAuthenticationFilter)");
		if (this.postOnly && !request.getMethod().equals("POST")) { 
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod()); 
		} else { 
			String username = this.obtainUsername(request); 
			String password = this.obtainPassword(request); 
			if (username == null) { 
				username = ""; 
			} 
			if (password == null) { 
				password = ""; 
			} 
			username = username.trim(); 
			UsernamePasswordAuthenticationToken authRequest = 
					new UsernamePasswordAuthenticationToken(username, password); 
			this.setDetails(request, authRequest); 
			return this.getAuthenticationManager().authenticate(authRequest); }
	}
}



