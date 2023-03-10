package com.sparta.crudassignment.dto;

import com.sparta.crudassignment.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
}
