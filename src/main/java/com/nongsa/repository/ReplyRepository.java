package com.nongsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nongsa.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	@Modifying
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	int mSave(long userId, int boardId, String content);//업데이트된 행의 개수 리턴
}
