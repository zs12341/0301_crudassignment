package com.sparta.crudassignment.service;

import com.sparta.crudassignment.dto.CommentRequestDto;
import com.sparta.crudassignment.dto.CommentResponseDto;
import com.sparta.crudassignment.dto.MessageResponse;
import com.sparta.crudassignment.dto.StatusEnum;
import com.sparta.crudassignment.entity.Comment;
import com.sparta.crudassignment.entity.Memo;
import com.sparta.crudassignment.entity.User;
import com.sparta.crudassignment.jwt.JwtUtil;
import com.sparta.crudassignment.repository.CommentRepository;
import com.sparta.crudassignment.repository.MemoRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CommentService {

    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public CommentService(MemoRepository memoRepository, CommentRepository commentRepository, JwtUtil jwtUtil) {
        this.memoRepository = memoRepository;
        this.commentRepository = commentRepository;
        this.jwtUtil = jwtUtil;
    }

    //댓글 작성
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){

        Memo memo = getMemoById(id);
        User user = jwtUtil.getUserCheck(httpServletRequest);

        Comment comment = commentRepository.save(new Comment(memo, commentRequestDto, user));
        return new CommentResponseDto(comment);
    }

    //댓글 수정
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){

        Memo memo = getMemoById(id);
        User user = jwtUtil.getUserCheck(httpServletRequest);
        Comment comment = getCommentById(id);

        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    public MessageResponse deleteComment(Long id, HttpServletRequest httpServletRequest){

        getMemoById(id);
        User user = jwtUtil.getUserCheck(httpServletRequest);
        Comment comment = getCommentById(id);

        commentRepository.deleteById(id);
        return new MessageResponse(StatusEnum.OK);
    }

    // MemoRepository에서 글을 가져옴
    public Memo getMemoById(Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 글이 없습니다.")
        );
    }
    // CommentRepository에서 댓글을 가져옴
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 댓글이 없습니다.")
        );
    }
}
