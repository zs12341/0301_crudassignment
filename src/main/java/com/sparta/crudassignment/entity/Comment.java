package com.sparta.crudassignment.entity;

import com.sparta.crudassignment.dto.CommentRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column
    private String username;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "MEMO_ID")
    private Memo memo;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Comment(Memo memo, CommentRequestDto commentRequestDto, User user){
        this.memo = memo;
        this.username = user.getUsername();
        this.comment = commentRequestDto.getComment();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();

    }
}
