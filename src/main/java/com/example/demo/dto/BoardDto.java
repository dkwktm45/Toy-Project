package com.example.demo.dto;

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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "board_id",updatable = false,insertable = false)
	private List<BoardParticipants> boardParticipantsList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;
}
