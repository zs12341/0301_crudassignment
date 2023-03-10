package com.sparta.crudassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MessageResponse {

    private final int status;
    private final String message;

    public MessageResponse(StatusEnum statusEnum) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
    }
}
