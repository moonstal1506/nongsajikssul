package com.nongsa.sns.dto;

import com.nongsa.sns.model.Board;
import com.nongsa.sns.model.Reply;
import com.nongsa.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private User user;
    private boolean likeState;
    private int likeCount;
    private int count;
    private List<Reply> replys;

    public BoardResponseDto(Board board, Long principalId) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.count = board.getCount();
        this.user = board.getUser();
        this.likeCount = board.getLikes().size();
        this.replys = board.getReplys();

        board.getLikes().forEach((like) -> {
            if (like.getUser().getId() == principalId) {
                this.likeState = true;
            }
        });
    }
}
