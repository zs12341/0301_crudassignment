package com.sparta.crudassignment.service;

import com.sparta.crudassignment.dto.MemoRequestDto;
import com.sparta.crudassignment.entity.Memo;
import com.sparta.crudassignment.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }



    @Transactional
    public Memo getMemo(Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 이름입니다.")
        );
        return memo;
    }


    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (requestDto.getPassword().equals(memo.getPassword())) {
            memo.update(requestDto);
            return memo.getId();
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    @Transactional
    public String deleteMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (requestDto.getPassword().equals(memo.getPassword())){
            memoRepository.deleteById(id);
            return "삭제 완료!";
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

}



