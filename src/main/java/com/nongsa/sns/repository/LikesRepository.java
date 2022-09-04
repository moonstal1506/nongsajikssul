package com.nongsa.sns.repository;

import com.nongsa.sns.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "insert into likes(boardId,userId,createDate) values(:boardId,:principalId,now())", nativeQuery = true)
    int likes(Long boardId, Long principalId);

    @Modifying
    @Query(value = "Delete from likes where boardId = :boardId and userId=:principalId", nativeQuery = true)
    int unLikes(Long boardId, Long principalId);
}
