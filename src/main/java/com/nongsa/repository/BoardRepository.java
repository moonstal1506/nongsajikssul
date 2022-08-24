package com.nongsa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nongsa.model.Board;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT * FROM board WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId =:principalId) order by id desc", nativeQuery = true)
    Page<Board> feed(Long principalId, Pageable pageable);

    @Query(value = "SELECT b.* FROM board b " +
            "LEFT OUTER JOIN (SELECT  boardId, COUNT(boardId) likeCount FROM likes GROUP BY boardId) c " +
            "on b.id=c.boardId ORDER BY likeCount DESC", nativeQuery = true)
    List<Board> popular();
}
