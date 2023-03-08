package com.sparta.crudassignment.controller;

import com.sparta.crudassignment.dto.MemoListResponseDto;
import com.sparta.crudassignment.dto.MemoRequestDto;
import com.sparta.crudassignment.dto.MemoResponseDto;
import lombok.RequiredArgsConstructor;
import com.sparta.crudassignment.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/memo") // 전체 메모 조회
    public List<MemoListResponseDto> getMemoList() {
        return memoService.getMemoList();
    }

    @PostMapping("/memo") // 메모 작성
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto, HttpServletRequest httpServletRequest) {
        return memoService.createMemo(memoRequestDto, httpServletRequest);
    }

    @GetMapping("/memo/{id}") // 특정 메모 조회
    public MemoListResponseDto getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PutMapping("/memo/{id}") // 메모 수정
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto, HttpServletRequest httpServletRequest) {
        return memoService.update(id, memoRequestDto, httpServletRequest);
    }

    @DeleteMapping("/memo/{id}") // 메모 삭제
    public ResponseEntity deleteMemo(@PathVariable Long id, HttpServletRequest httpServletRequest){
        return ResponseEntity.ok().body(memoService.delete(id, httpServletRequest));
    }
}
