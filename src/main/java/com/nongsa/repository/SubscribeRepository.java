package com.nongsa.repository;

import com.nongsa.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(value = "insert into subscribe(fromUserId,toUserId,createDate) values(:fromUserId,:toUserId,now())", nativeQuery = true)
    void subscribe(int fromUserId, int toUserId);

    @Modifying
    @Query(value = "delete from subscribe where fromUserId=:fromUserId and toUserId=:toUserId", nativeQuery = true)
    void unSubscribe(int fromUserId, int toUserId);

    @Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId=:pageUserId",nativeQuery=true)
    int subscribeState(int principalId,int pageUserId);

    @Query(value="SELECT COUNT(*) FROM subscribe WHERE fromUserId =:pageUserId",nativeQuery=true)
    int subscribeCount(int pageUserId);

    @Query(value="SELECT COUNT(*) FROM subscribe WHERE toUserId =:pageUserId",nativeQuery=true)
    int subscribedCount(int pageUserId);
}
