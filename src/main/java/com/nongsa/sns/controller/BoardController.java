package com.nongsa.sns.controller;

import com.nongsa.config.auth.PrincipalDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nongsa.sns.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model,
                        @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findAll(pageable));
        return "index";
    }

    @GetMapping("/popular")
    public String popular(Model model) {
        model.addAttribute("boards", boardService.findAllPopular());
        return "popular";

    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        boardService.updateCount(id);
        model.addAttribute("board", boardService.findById(id, principalDetails.getUser().getId()));

        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.updateForm(id));
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
        model.addAttribute("boards", boardService.feed(principalDetails.getUser().getId(), pageable));
        return "feed";
    }
}
