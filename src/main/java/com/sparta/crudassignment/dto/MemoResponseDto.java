package com.sparta.crudassignment.dto;

import com.sparta.crudassignment.entity.Memo;

public class MemoResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String contents;


    public MemoResponseDto(Memo memo) {

        this.id = memo.getId();
        this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();

    }

    public Long getId() {
        return this.id;
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
}
