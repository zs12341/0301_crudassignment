package com.sparta.crudassignment.service;

import com.sparta.crudassignment.dto.CommentRequestDto;
import com.sparta.crudassignment.dto.CommentResponseDto;
import com.sparta.crudassignment.entity.Comment;
import com.sparta.crudassignment.entity.Memo;
import com.sparta.crudassignment.entity.User;
import com.sparta.crudassignment.jwt.JwtUtil;
import com.sparta.crudassignment.repository.CommentRepository;
import com.sparta.crudassignment.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private MemoRepository memoRepository;
    private CommentRepository commentRepository;
    private JwtUtil jwtUtil;

    //댓글 작성
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 글이 없습니다.")
        );
        User user = jwtUtil.getUserInfo(httpServletRequest);

        Comment comment = commentRepository.save(new Comment(memo, commentRequestDto, user));
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 글이 없습니다.")
        );
        User user = jwtUtil.getUserInfo(httpServletRequest);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 댓글이 없습니다.")
        );
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);



    }

}
