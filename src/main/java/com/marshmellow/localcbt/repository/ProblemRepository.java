package com.marshmellow.localcbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marshmellow.localcbt.entity.Problem;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long>{
    Optional<Problem> findById(long id);
    boolean existsById(long id);

    //제목으로 찾기
    //List<Problem> findByQuestionContaining(String keyword);
}
