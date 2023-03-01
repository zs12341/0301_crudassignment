package com.sparta.crudassignment.Service;

import com.sparta.crudassignment.Dto.MemoRequestDto;
import com.sparta.crudassignment.Entity.Memo;
import com.sparta.crudassignment.Repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public Memo createMemo(MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }
}
