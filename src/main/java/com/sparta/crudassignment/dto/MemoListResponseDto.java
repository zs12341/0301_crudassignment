package com.sparta.crudassignment.dto;

import com.sparta.crudassignment.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoListResponseDto {
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    //생성자 까지는 알겠는데 이 안에 메모가 왜 들어가지?
    public MemoListResponseDto(Memo memo) {
        this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
    }
}
