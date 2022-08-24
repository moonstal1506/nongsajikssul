package com.nongsa.service;

import com.nongsa.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;

    @Transactional
    public void 좋아요(Long boardId, Long principalId) {
        likesRepository.likes(boardId, principalId);
    }

    @Transactional
    public void 좋아요취소(Long boardId, Long principalId) {
        likesRepository.unLikes(boardId, principalId);
    }
}
