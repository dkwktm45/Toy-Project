package com.example.demo.service;

import com.example.demo.exception.AllowMe;
import com.example.demo.exception.Already;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Board;
import com.example.demo.model.BoardParticipants;
import com.example.demo.model.redis.ChatMessage;
import com.example.demo.model.redis.ChatRoom;
import com.example.demo.repo.*;
import com.example.demo.response.BoardDto;
import com.example.demo.response.BoardParticipantsDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardParticipantService {
	private final Logger logger = LoggerFactory.getLogger(BoardParticipantService.class);


	private final BoardParticipantsRepository participantsRepository;
	private final BoardService boardService;
	private final UserService userService;
	private final BoardRepository boardRepository;
	private final ChatService chatService;

	public Map<String, Object> setChat(Long boardId, Long userId, String name) throws Exception {
		Board board = boardService.getOne(boardId);
		if(board.getBoardParticipantsList().stream().anyMatch(info -> info.getUserId().equals(userId)) && board.getBoardParticipantsList() != null){
			throw new Already();
		}else if(board.getBoardWriter().equals(name)){
			throw new AllowMe();
		}else {
			ChatRoom chatRoom = chatService.createChat();
			BoardParticipants boardParticipants = BoardParticipants.builder()
					.board(board)
					.userName(name)
					.userId(userId)
					.roomId(chatRoom.getRoomId())
					.build();
			participantsRepository.save(boardParticipants);
			BoardParticipantsDto participantsDto = new BoardParticipantsDto(boardParticipants);
			Map<String, Object> result = new HashMap<>();
			result.put("boardId",participantsDto.getBoardDto().getBoardId());
			result.put("participants", participantsDto);
			return result;
		}
	}

	public List<BoardParticipantsDto> myChatRoom(Long userId){
		logger.info("myChatRoom start");
		List<BoardParticipantsDto> boardList = participantsRepository.findByBoard(userId)
				.stream().map(BoardParticipantsDto::new).collect(Collectors.toList());
		logger.info("myChatRoom complete");
		return boardList;
	}

	public List<BoardParticipantsDto> otherChatRoom(Long userId){
		logger.info("otherChatRoom ");
		return participantsRepository.findByUserId(userId)
				.stream().map(BoardParticipantsDto::new).collect(Collectors.toList());
	}

	public List<BoardParticipants> deleteRoom(Long boardId, Long participantId) {
		logger.info("deleteRoom start");
		Board board = boardRepository.findById(boardId).get();

		List<BoardParticipants> boardParticipantsList = board.getBoardParticipantsList()
				.stream().filter(info-> info.getParticipantId() !=participantId).collect(Collectors.toList());

		boardParticipantsList.stream().forEach(info -> info.setBoard(null));

		for(BoardParticipants participants : boardParticipantsList){
			chatService.sendChatMessage(
					ChatMessage.builder()
							.message(board.getUser().getUserName() + "님이 다른회원과 매칭이 되었습니다. 다음기회에 도전해주세요!")
							.roomId(participants.getRoomId())
							.build()
			);
		}
		logger.info("deleteRoom end");
		return boardParticipantsList;
	}

	public BoardParticipants findByRoomId(String roomId) {
		Optional<BoardParticipants> participantsOptional = participantsRepository.findByRoomId(roomId);
		if(participantsOptional.isPresent()){
			return participantsOptional.get();
		}else{
			return null;
		}
	}
}
