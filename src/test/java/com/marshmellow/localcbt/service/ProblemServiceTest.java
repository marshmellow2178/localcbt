package com.marshmellow.localcbt.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.marshmellow.localcbt.entity.Problem;
import com.marshmellow.localcbt.repository.ProblemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

public class ProblemServiceTest {
    //가짜 리포지토리 생성
    @Mock
    private ProblemRepository repo;

    @InjectMocks
    private ProblemService service;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProblem_returnsSavedId() {
        // given(객체 생성)
        // save() 호출 시점의 인스턴스에 ID를 직접 주입하고 리턴하도록 설정
        when(repo.save(any(Problem.class))).thenAnswer(invocation -> {
            Problem arg = invocation.getArgument(0);
            // private id 필드에 123L 설정
            ReflectionTestUtils.setField(arg, "id", 123L);
            return arg;
        });

        // when
        long result = service.createProblem("Q", "A");

        // then
        assertEquals(123L, result);
        verify(repo, times(1)).save(any(Problem.class));
    }

    @Test
    void checkAnswer(){
        //given
        String answer = "A";

        //then
        assertTrue(service.checkAnswer(answer, "A"));
        assertTrue(service.checkAnswer(answer, "a"));
        assertFalse(service.checkAnswer(answer, "B"));
    }
}
