package com.sparta.crudassignment.entity;

import com.sparta.crudassignment.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMO_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)//얘가 외래키
    private User user;

    @OneToMany(mappedBy = "memo", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;


    public Memo(MemoRequestDto memoRequestDto, User user){
        this.title = memoRequestDto.getTitle();
        this.username = user.getUsername();
        this.contents = memoRequestDto.getContents();
        this.user = user;
    }

    public void update(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        this.contents = memoRequestDto.getContents();
    }
}