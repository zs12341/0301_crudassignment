package com.sparta.crudassignment.entity;

import com.sparta.crudassignment.dto.CommentRequestDto;

import javax.persistence.*;

@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column
    private String username;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "MEMO_ID", nullable = false)
    private Memo memo;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Comment(Memo memo, CommentRequestDto commentRequestDto, User user){
        this.memo = memo;
        this.user = user;
        this.username = user.getUsername();
        this.comment = commentRequestDto.getComment();
    }

    public Comment() {
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();

    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getComment() {
        return this.comment;
    }

    public Memo getMemo() {
        return this.memo;
    }

    public User getUser() {
        return this.user;
    }
}
