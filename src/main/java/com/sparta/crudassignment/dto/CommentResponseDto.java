package com.sparta.crudassignment.dto;

import com.sparta.crudassignment.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {

    private final Long id;
    private final String username;
    private final String comment;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }
}
