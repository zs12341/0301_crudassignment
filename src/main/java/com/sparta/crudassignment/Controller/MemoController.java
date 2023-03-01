package com.sparta.crudassignment.Controller;

import com.sparta.crudassignment.Dto.MemoRequestDto;
import com.sparta.crudassignment.Entity.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/memos")
    public List<Memo> createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }
}
