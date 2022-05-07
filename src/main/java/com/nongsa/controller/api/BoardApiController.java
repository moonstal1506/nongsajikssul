package com.nongsa.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nongsa.config.auth.PrincipalDetails;
import com.nongsa.dto.ReplySaveRequestDto;
import com.nongsa.dto.ResponseDto;
import com.nongsa.model.Board;
import com.nongsa.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	
	
	private final BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseEntity<?> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseEntity<>(new ResponseDto<>(1,"글쓰기성공",null),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseEntity<>(new ResponseDto<>(1,"글삭제성공",null),HttpStatus.OK);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Board board){
		boardService.글수정하기(id, board);
		return new ResponseEntity<>(new ResponseDto<>(1,"글수정성공",null),HttpStatus.OK);
		
	}
	
	@PostMapping("/api/board/{boarId}/reply")
	public ResponseEntity<?> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseEntity<>(new ResponseDto<>(1,"댓글쓰기성공",null),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseEntity<?> replyDelete(@PathVariable int replyId) {
		boardService.댓글삭제(replyId);
		return new ResponseEntity<>(new ResponseDto<>(1,"댓글삭제성공",null),HttpStatus.OK);
	}
}
