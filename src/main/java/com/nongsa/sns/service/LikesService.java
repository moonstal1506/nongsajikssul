package com.nongsa.sns.service;

import com.nongsa.sns.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;

    @Transactional
    public void likes(Long boardId, Long principalId) {
        likesRepository.likes(boardId, principalId);
    }

    @Transactional
    public void unLikes(Long boardId, Long principalId) {
        likesRepository.unLikes(boardId, principalId);
    }
}
