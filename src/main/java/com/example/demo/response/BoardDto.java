package com.example.demo.response;


import com.example.demo.model.Board;
import com.example.demo.model.BoardParticipants;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	private String boardWriter;
	private String boardTitle;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id",updatable = false,insertable = false)
	private List<BoardParticipantsDto> boardParticipantsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private UserDto userDto;

	public BoardDto(Board board) {
		this.boardId = board.getBoardId();
		this.boardWriter = board.getBoardWriter();
		this.boardTitle = board.getBoardTitle();
		this.userDto = new UserDto(board.getUser());
		this.boardId = board.getBoardId();
	}
}

