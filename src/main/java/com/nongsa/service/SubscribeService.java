package com.nongsa.service;

import com.nongsa.dto.SubscribeDto;
import com.nongsa.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional(readOnly = true)
    public List<SubscribeDto> following(Long principalId, Long pageUserId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, ");
        sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId=? AND toUserId=u.id),1,0) subscribeState, ");
        sb.append("if((?=u.id),1,0) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId =? ");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
        return subscribeDtos;
    }

    @Transactional(readOnly = true)
    public List<SubscribeDto> follower(Long principalId, Long pageUserId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, ");
        sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId=? AND toUserId=u.id),1,0) subscribeState, ");
        sb.append("if((?=u.id),1,0) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.fromUserId ");
        sb.append("WHERE s.toUserId =? ");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
        return subscribeDtos;
    }

    @Transactional
    public void follow(Long fromUserId, Long toUserId) {
        try {
            subscribeRepository.subscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new IllegalStateException("이미 구독하였습니다.");
        }
    }

    @Transactional
    public void unfollow(Long fromUserId, Long toUserId) {
        subscribeRepository.unSubscribe(fromUserId, toUserId);
    }
}
