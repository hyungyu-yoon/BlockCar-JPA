package com.bollock.blockcar.user;

import java.util.List;

public interface IUserService {
	User insertUser(User user);

	List<User> findUserAll();

	User findUser(Long userno);

	User updateUser(User user);

	void deleteUser(User user);
}
