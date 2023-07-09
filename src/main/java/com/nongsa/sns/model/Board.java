package com.nongsa.sns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nongsa.model.BaseEntity;
import com.nongsa.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    private int count;

    @JsonIgnoreProperties({"boards"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replys;

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Likes> likes;

    public void updateCount(){
        count++;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

