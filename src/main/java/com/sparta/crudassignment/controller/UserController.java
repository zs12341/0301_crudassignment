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
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok().body(userService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseEntity.ok().body(userService.login(loginRequestDto, response));
    }

}