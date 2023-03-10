package com.sparta.crudassignment.dto;

public enum StatusEnum {
    OK(200, "OK");

    int statusCode;
    String msg;

    StatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
