package com.nongsa.sns.repository;

import com.nongsa.sns.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>,
        QuerydslPredicateExecutor<Board>, BoardRepositoryCustom {

    @Query(value = "SELECT * FROM board WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId =:principalId) order by id desc", nativeQuery = true)
    Page<Board> feed(Long principalId, Pageable pageable);

    @Query(value = "SELECT b.* FROM board b " +
            "LEFT OUTER JOIN (SELECT  boardId, COUNT(boardId) likeCount FROM likes GROUP BY boardId) c " +
            "on b.id=c.boardId ORDER BY likeCount DESC", nativeQuery = true)
    List<Board> popular();
}
