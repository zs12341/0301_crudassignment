package com.sparta.crudassignment.controller;

import com.sparta.crudassignment.dto.CommentRequestDto;
import com.sparta.crudassignment.dto.CommentResponseDto;
import com.sparta.crudassignment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody @Valid CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
       return commentService.createComment(id, commentRequestDto, httpServletRequest);
    }

    @PutMapping("/{id} ")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody @Valid CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.updateComment(id, commentRequestDto, httpServletRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id, HttpServletRequest httpServletRequest){
        return ResponseEntity.ok().body(commentService.deleteComment(id, httpServletRequest));
    }
}
