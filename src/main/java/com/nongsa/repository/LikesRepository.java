package com.nongsa.repository;

import com.nongsa.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

    @Modifying
    @Query(value = "insert into likes(boardId,userId,createDate) values(:boardId,:principalId,now())", nativeQuery = true)
    int likes(int boardId, int principalId);

    @Modifying
    @Query(value = "Delete from likes where boardId = :boardId and userId=:principalId", nativeQuery = true)
    int unLikes(int boardId, int principalId);
}
