package com.hk.board.daos;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.UserDto;

@Repository
public class UserDao implements IUserDao{

	private String namespance="com.hk.board.";
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserDto getUserById(String id) {
		return sqlSession.selectOne(namespance+"getUserById", id);
	}
	
	public boolean insertUser(UserDto dto) {
		int count=sqlSession.insert(namespance+"insertUser", dto);
		return count>0?true:false;
	}
}






