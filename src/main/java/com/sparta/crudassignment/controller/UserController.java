package com.sparta.crudassignment.controller;

import com.sparta.crudassignment.dto.LoginRequestDto;
import com.sparta.crudassignment.dto.MessageResponse;
import com.sparta.crudassignment.dto.SignupRequestDto;
import com.sparta.crudassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController { //rest api // dto 로
    // UserService 인터페이스를 구현한 구현체를 주입받기 위한 필드 :: (필드는 객체 내부의 변수이다)
    // @RequirgsConstructor가 자동으로 생성자를 만들어줄때 그 매개변수로 userService를 사용하기 때문에
    // 코드의 결합도를 낮추면서 의존성을 주입받는다. UserService에 변경이 있더라도 외부에서 주입받기 때문에
    // UserController 단에서는 수정할 필요가 없어진다. >> 의존성 주입, 객체지향의 캡슐화
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        //200 ok 코드를 반환하는데 그 바디에 userService.signup(signupRequestDto)를 담아서 같이 반환해라
        return ResponseEntity.ok().body(userService.signup(signupRequestDto));
    }


    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseEntity.ok().body(userService.login(loginRequestDto, response));
    }

}