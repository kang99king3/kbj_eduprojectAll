package com.hk.board.daos;

import com.hk.board.dtos.UserDto;

public interface IUserDao {

	public UserDto getUserById(String username);
	public boolean insertUser(UserDto dto);
}
