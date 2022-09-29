package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.model.redis.ChatRoom;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.response.UserDto;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;


	public UserDto saveUser(User user) {
		Optional<User> memoryUser = userRepository.findByUserName(user.getUserName());
		if (memoryUser.isEmpty()) {
			User res = User.builder().userName(user.getUserName()).token(jwtTokenProvider.generateToken(user.getUserName())).build();
			userRepository.save(res);
			return new UserDto(res);
		}
		return new UserDto(memoryUser.get());
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
}
