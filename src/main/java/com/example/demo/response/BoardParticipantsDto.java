package com.example.demo.response;

import com.example.demo.model.BoardParticipants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardParticipantsDto {
	private Long participantId; // 방번호
	private String userName; // 메시지 name
	private Long userId;
	private String roomId;
	private String boardTitle;
	private String boardWriter;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "board_id")
	private BoardDto boardDto;

	public BoardParticipantsDto(BoardParticipants participants){
		this.participantId = participants.getParticipantId();
		this.userName = participants.getUserName();
		this.userId = participants.getUserId();
		this.roomId = participants.getRoomId();
		this.boardWriter = participants.getBoard().getBoardWriter();
		this.boardTitle = participants.getBoard().getBoardTitle();
		this.boardDto = new BoardDto(participants.getBoard());
	}

}
