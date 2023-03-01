package com.sparta.crudassignment.Entity;

import com.sparta.crudassignment.Dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    public Memo(MemoRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}