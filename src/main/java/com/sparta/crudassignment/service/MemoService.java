package com.sparta.crudassignment.service;

import com.sparta.crudassignment.dto.*;
import com.sparta.crudassignment.entity.Memo;
import com.sparta.crudassignment.entity.User;
import com.sparta.crudassignment.jwt.JwtUtil;
import com.sparta.crudassignment.repository.MemoRepository;
import com.sparta.crudassignment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true) // 메모 전체 조회
    public List<MemoListResponseDto> getMemoList() {

        List<MemoListResponseDto> memoListResponseDto = new ArrayList<>();
        List<Memo> memoList = memoRepository.findAllByOrderByModifiedAtDesc();
        for(Memo memo : memoList) {
            memoListResponseDto.add(new MemoListResponseDto(memo));
        }
        return memoListResponseDto;
    }

    @Transactional // 메모 작성
    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto, HttpServletRequest httpServletRequest){

        User user = getUserCheck(httpServletRequest);
        Memo memo = memoRepository.saveAndFlush(new Memo(memoRequestDto, user));
        return new MemoResponseDto(memo);
    }

    @Transactional(readOnly = true) // 특정 메모 조회
    public MemoListResponseDto getMemo(Long id){

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("일치하는 글이 없습니다.")
        );
        return new MemoListResponseDto(memo);
    }

    @Transactional // 메모 수정
    public MemoResponseDto update(Long id, MemoRequestDto memoRequestDto, HttpServletRequest httpServletRequest){

        User user = getUserCheck(httpServletRequest);
        Memo memo = memoRepository.findByIdAndUserId(id, user.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("수정할 게시글이 존재하지 않습니다.")
        );
        memo.update(memoRequestDto);
        return new MemoResponseDto(memo);
    }

    @Transactional // 메모 삭제
    public MessageResponse delete (Long id, HttpServletRequest httpServletRequest) {

        User user = getUserCheck(httpServletRequest);
        Memo memo = memoRepository.findByIdAndUserId(id, user.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.")
        );
        memoRepository.deleteById(id);
        return new MessageResponse(StatusEnum.OK);
    }

    //사용자 확인 중복코드
    public User getUserCheck(HttpServletRequest httpServletRequest) {

        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        if (token != null) {

            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
            );
            return user;
        }
        throw new IllegalArgumentException("토큰이 없습니다.");
    }
}



