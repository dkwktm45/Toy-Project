package com.example.demo.repo;

import com.example.demo.dto.BoardParticipantsDto;
import com.example.demo.model.BoardParticipants;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardParticipantsRepository extends JpaRepository<BoardParticipants, Long> {
	@EntityGraph(attributePaths = "board")
	Optional<List<BoardParticipants>> findByUserId(Long userId);

	Optional<BoardParticipants> findByRoomId(String roomId);

/*
	@Query(value = "SELECT " +
			"new com.project.MyDuo.dto.Board.BoardDetailBaseDto(b.micEnabled, b.content, b.lolPuuid, m.name, m.email, m.heart, m.valid)" +
			"FROM Board b " +
			"INNER JOIN Member m " +
			"ON b.member.id = m.id AND b.uuid = :boardUUID")
	*/
	@Query(value = "select * from board_participants p " +
			"left outer join board b " +
			"on b.board_id = p.board_id where b.user_id = :user_id" , nativeQuery = true)
	List<BoardParticipants> findByBoard(@Param("user_id") Long userId);
}
