package com.sparta.crudassignment.repository;

import com.sparta.crudassignment.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByOrderByModifiedAtDesc();

    Optional<Memo> findByIdAndUsername(Long id, String username);
}