package com.nongsa.sns.controller.api;

import com.nongsa.sns.dto.BoardSaveRequestDto;
import com.nongsa.sns.dto.BoardUpdateRequestDto;
import com.nongsa.sns.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nongsa.config.auth.PrincipalDetails;
import com.nongsa.sns.dto.ReplySaveRequestDto;
import com.nongsa.common.dto.ResponseDto;
import com.nongsa.sns.service.BoardService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final LikesService likesService;

    @PostMapping("/api/board")
    public ResponseEntity<?> save(@RequestBody BoardSaveRequestDto saveRequestDto, @AuthenticationPrincipal PrincipalDetails principal) {
        boardService.save(saveRequestDto, principal.getUser());
        return new ResponseEntity<>(new ResponseDto<>(1, "글쓰기성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boardService.delete(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "글삭제성공", null), HttpStatus.OK);
    }

    @PutMapping("/api/board/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto updateRequestDto) {
        boardService.update(id, updateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "글수정성공", null), HttpStatus.OK);

    }

    @PostMapping("/api/board/{boarId}/reply")
    public ResponseEntity<?> replySave(@Valid @RequestBody ReplySaveRequestDto replySaveRequestDto, BindingResult bindingResult) {
        boardService.saveReply(replySaveRequestDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "댓글쓰기성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseEntity<?> replyDelete(@PathVariable Long replyId) {
        boardService.deleteReply(replyId);
        return new ResponseEntity<>(new ResponseDto<>(1, "댓글삭제성공", null), HttpStatus.OK);
    }

    @PostMapping("/board/{boardId}/likes")
    public ResponseEntity<?> likes(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.likes(boardId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/board/{boardId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.unLikes(boardId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요취소성공", null), HttpStatus.OK);
    }
}
