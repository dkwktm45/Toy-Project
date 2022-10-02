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


	public UserDto saveUser(User user) throws Exception {
		if (user.getUserName().isEmpty()) {
			throw new IllegalStateException("이름이 없습니다.");
		}

		Optional<User> memoryUser = Optional.ofNullable(userRepository.findByUserName(user.getUserName()).orElse(null));
		if (memoryUser.isEmpty()) {
			User res = User.builder().userName(user.getUserName()).token(jwtTokenProvider.generateToken(user.getUserName())).build();
			userRepository.save(res);
			return UserDto.builder().userId(res.getUserId()).token(res.getToken()).userName(res.getUserName()).build();
		} else {
			return new UserDto(memoryUser.get());
		}

	}
}
