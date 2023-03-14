package com.sparta.crudassignment.entity;

import com.sparta.crudassignment.dto.MemoRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Memo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO면 각각 따로 나오는것처럼 보임.
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

    //재할당할 일 없으므로 final로 선언하지 않을 이유가 없다.
    @OneToMany(mappedBy = "memo", cascade = CascadeType.REMOVE)
    private final List<Comment> commentList = new ArrayList<>();


    public Memo(MemoRequestDto memoRequestDto, User user){
        this.title = memoRequestDto.getTitle();
        this.username = user.getUsername();
        this.contents = memoRequestDto.getContents();
        this.user = user;
    }

    //기본 생성자 : 매개변수가 있는 생성자가 정의되어 있으므로 사용해야 한다.
    public Memo() {
    }

    public void update(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        this.contents = memoRequestDto.getContents();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUsername() {
        return this.username;
    }

    public String getContents() {
        return this.contents;
    }

    public User getUser() {
        return this.user;
    }

}