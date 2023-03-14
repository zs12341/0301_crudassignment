package com.sparta.crudassignment.dto;

public class MessageResponse {

    private final int status;
    private final String message;

    public MessageResponse(StatusEnum statusEnum) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
