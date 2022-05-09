package com.nongsa.controller;

import com.nongsa.config.auth.PrincipalDetails;
import com.nongsa.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nongsa.service.BoardService;

import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
@Controller
public class BoardController {


    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model,
                        @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model,
                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        model.addAttribute("board", boardService.글상세보기(id, principalDetails.getUser().getId()));

        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.수정페이지(id));
        return "board/updateForm";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/feed")
    public String feed(Model model,
                       @AuthenticationPrincipal PrincipalDetails principalDetails,
                       @PageableDefault(size = 3) Pageable pageable) {
        model.addAttribute("boards", boardService.피드보기(principalDetails.getUser().getId(), pageable));
        return "feed";
    }
}
