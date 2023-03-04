package com.sparta.crudassignment.controller;

import com.sparta.crudassignment.dto.MemoRequestDto;
import com.sparta.crudassignment.entity.Memo;
import lombok.RequiredArgsConstructor;
import com.sparta.crudassignment.service.MemoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/api/memos/{id}")
    public Memo getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.deleteMemo(id, requestDto);
    }
}
