package com.sparta.crudassignment.dto;

import com.sparta.crudassignment.entity.Memo;

import java.time.LocalDateTime;

public class MemoListResponseDto {

    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public MemoListResponseDto(Memo memo) {
        this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }

    public String getUsername() {
        return this.username;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContents() {
        return this.contents;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }
}
