package com.hk.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hk.board.HomeController;
import com.hk.board.daos.IUserDao;
import com.hk.board.daos.UserDao;
import com.hk.board.dtos.UserDto;

@Service
public class UserService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Welcome UserService! loadUserByUsername {}.",username);
		System.out.println("userDao:"+userDao);
		UserDto user=userDao.getUserById(username);
		
		System.out.println(user);
		if(null == user) {
            throw new UsernameNotFoundException("User Not Found");
        }
		return user;
	}
	
	public boolean insertUser(UserDto userDto) {
		System.out.println("입력한 비밀번호:"+userDto.getPassword());
		userDto.setPassword(bcryptPasswordEncoder.encode(userDto.getPassword()));
		System.out.println("암호화비밀번호:"+userDto.getPassword());
		return userDao.insertUser(userDto);
	}

}
