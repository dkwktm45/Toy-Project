package com.example.demo.response;

import com.example.demo.model.User;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long userId;
	private String userName;
	private String userPwd;
	private String token;

	@OneToMany(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id",updatable = false,insertable = false)
	private List<BoardDto> boardList;

	public UserDto(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
	}
}
