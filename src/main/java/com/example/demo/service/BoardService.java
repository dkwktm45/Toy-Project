package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.repo.BoardRepository;
import com.example.demo.response.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	public BoardDto createBoard(Board board) {
		boardRepository.save(board);
		return new BoardDto(board);
	}

	public List<BoardDto> findAllBoard() {
		return boardRepository.findAll().stream().map(BoardDto::new).collect(Collectors.toList());
	}

	public Board getOne(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow(NullPointerException::new);
	}

	public Map<String,Object> getBoardId(Long participantId) {
		Optional<Board> board = boardRepository.findByParticipantId(participantId);
		if (board.isPresent()) {
			Map<String,Object> result = new HashMap<>();
			BoardDto boardDto = new BoardDto(board.get());
			result.put("board" , boardDto);
			result.put("userName",boardDto.getUserDto().getUserName());
			return result;
		}
		return null;
	}
}
