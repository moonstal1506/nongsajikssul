package com.nongsa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nongsa.model.Board;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query(value = "SELECT * FROM board WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId =:principalId) order by id desc", nativeQuery = true)
    Page<Board> feed(int principalId, Pageable pageable);
}
