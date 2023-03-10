package com.sparta.crudassignment.dto;

import com.sparta.crudassignment.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
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
}
