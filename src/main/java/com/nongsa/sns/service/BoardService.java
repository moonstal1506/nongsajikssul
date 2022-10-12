package com.nongsa.sns.service;

import com.nongsa.handler.exception.CustomException;
import com.nongsa.sns.dto.BoardSearchDto;
import com.nongsa.sns.dto.ReplySaveRequestDto;
import com.nongsa.sns.model.Board;
import com.nongsa.sns.model.Reply;
import com.nongsa.sns.repository.BoardRepository;
import com.nongsa.sns.repository.ReplyRepository;
import com.nongsa.user.model.User;
import com.nongsa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void save(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> findAll(BoardSearchDto boardSearchDto, Pageable pageable) {
        return boardRepository.getBoardListPage(boardSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public List<Board> findAllPopular() {
        return boardRepository.popular();
    }

    @Transactional(readOnly = true)
    public Board findById(Long id, Long principalId) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException("글상세보기 실패: 아이디를 찾을 수 없습니다."));
        board.setLikeCount(board.getLikes().size());
        board.getLikes().forEach((like) -> {
            if (like.getUser().getId() == principalId) {
                board.setLikeState(true);
            }
        });
        return board;
    }

    @Transactional(readOnly = true)
    public Board updateForm(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new CustomException("글상세보기 실패: 아이디를 찾을 수 없습니다."));
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다."));
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void updateCount(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다."));
        board.updateCount();
    }

    @Transactional
    public void saveReply(ReplySaveRequestDto replySaveRequestDto) {
        Reply reply = new Reply(
                replySaveRequestDto.getContent(),
                boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패")),
                userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저 찾기 실패"))
                );
        replyRepository.save(reply);
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
