package com.example.demo.repo;

import com.example.demo.model.BoardParticipants;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardParticipantsRepository extends JpaRepository<BoardParticipants, Long> {
	@EntityGraph(attributePaths = "board")
	List<BoardParticipants> findByUserId(Long userId);

	Optional<BoardParticipants> findByRoomId(String roomId);

	@Query(value = "select * from board_participants p " +
			"left outer join board b " +
			"on b.board_id = p.board_id where b.user_id = :user_id" , nativeQuery = true)
	List<BoardParticipants> findByBoard(@Param("user_id") Long userId);
}
