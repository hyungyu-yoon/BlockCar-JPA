package com.bollock.blockcar.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User insertUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findUserAll() {
		return userRepository.findAll();
	}

	@Override
	public User findUser(Long userno) {
		return userRepository.findById(userno).get();
	}

	@Override
	public User updateUser(User user) {
		user.setPassword(user.getPassword());
		user.setType(user.getType());
		userRepository.flush();
		return user;
	}
}
