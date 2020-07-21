package com.hk.board.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hk.board.dtos.UserDto;
import com.hk.board.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserService userService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication; //유저가 입력한 정보를 이이디비번으으로만든다.(로그인한 유저아이디비번정보를담는다)
		 	System.out.println("CustomAuthenticationProvider");
		 	System.out.println("authToken:"+authToken.getName()+","+authToken.getCredentials());
		    
		 	UserDto userInfo = (UserDto) userService.loadUserByUsername(authToken.getName()); //UserDetailsService에서 유저정보를 불러온다.
		    System.out.println("userInfo:"+userInfo);
		    
		    if (userInfo == null) {
		      throw new UsernameNotFoundException(authToken.getName());
		    }
		    
		    System.out.println(bcryptPasswordEncoder.matches((CharSequence) authToken.getCredentials(), userInfo.getPassword()));
		    if (!bcryptPasswordEncoder.matches((CharSequence) authToken.getCredentials(), userInfo.getPassword())) {
		    	System.out.println("패스워드 틀림");
		      throw new BadCredentialsException("not matching username or password");
		    }
//		    if (!matchPassword(userInfo.getPassword(), authToken.getCredentials())) {
//		    	System.out.println("패스워드 틀림");
//		      throw new BadCredentialsException("not matching username or password");
//		    }

		    List<GrantedAuthority> authorities = (List<GrantedAuthority>) userInfo.getAuthorities();

		    return new UsernamePasswordAuthenticationToken(userInfo.getId(),null,authorities);
	}

	private boolean matchPassword(String password, Object credentials) {
	    return password.equals(credentials);
	}
	  
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
