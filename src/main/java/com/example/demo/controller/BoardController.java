package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.response.BoardDto;
import com.example.demo.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardService;

	@PostMapping(value = "/board-create")
	public BoardDto createBoard(@RequestBody Board board){
		logger.info("board-create");
		return boardService.createBoard(board);
	}

	@PostMapping(value = "/board-all")
	public ResponseEntity<List<BoardDto>> findAll(){
		logger.info("board-all");
		List<BoardDto> boardDtoList = boardService.findAllBoard();
		return ResponseEntity.ok(boardDtoList);
	}

	@PostMapping(value = "/board-id")
	public ResponseEntity<Map<String,Object>> findById(@RequestBody Long participantId){
		logger.info("board-Id");
		return ResponseEntity.ok(boardService.getBoardId(participantId));
	}
}
