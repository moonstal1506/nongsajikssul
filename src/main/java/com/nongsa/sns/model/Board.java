package com.nongsa.sns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nongsa.model.BaseEntity;
import com.nongsa.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replys;

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board")
    private List<Likes> likes;

    @Transient
    private boolean likeState;

    @Transient
    private int likeCount;

}

