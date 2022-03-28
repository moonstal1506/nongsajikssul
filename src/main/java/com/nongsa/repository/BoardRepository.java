package com.nongsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nongsa.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
