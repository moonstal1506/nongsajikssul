package com.nongsa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nongsa.dto.ReplySaveRequestDto;
import com.nongsa.model.Board;
import com.nongsa.model.User;
import com.nongsa.repository.BoardRepository;
import com.nongsa.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void save(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public List<Board> findAllPopular() {
        return boardRepository.popular();
    }

    @Transactional(readOnly = true)
    public Board findById(Long id, Long principalId) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글상세보기 실패: 아이디를 찾을 수 없습니다."));
        board.setLikeCount(board.getLikes().size());
        board.getLikes().forEach((like) -> {
            if (like.getUser().getId() == principalId) {
                board.setLikeState(true);
            }
        });
        return board;
    }

    @Transactional(readOnly = true)
    public Board findByIdUpdate(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글상세보기 실패: 아이디를 찾을 수 없습니다."));
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void findByIdUpdate(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다."));
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void saveReply(ReplySaveRequestDto replySaveRequestDto) {
        replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(),
                replySaveRequestDto.getContent());
    }

    @Transactional
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    @Transactional(readOnly = true)
    public Page<Board> feed(Long principalId, Pageable pageable) {
        return boardRepository.feed(principalId, pageable);
    }
}
