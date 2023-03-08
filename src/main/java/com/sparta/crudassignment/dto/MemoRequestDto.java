package com.sparta.crudassignment.dto;

import lombok.Getter;

@Getter // @ToString :
public class MemoRequestDto {
    private String title;
    private String contents;
    private String username;

}
