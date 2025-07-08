package com.marshmellow.localcbt.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemTest {
    @Test
    void create_shouldSetFieldsCorrectly() {
        // given
        String q = "테스트 문제";
        String a = "테스트 답변";

        // when
        Problem p = Problem.create(q, a);

        // then
        assertEquals(q, p.getQuestion(), "question 필드가 올바르게 세팅되어야 한다");
        assertEquals(a, p.getAnswer(),   "answer 필드가 올바르게 세팅되어야 한다");
        assertNotNull(p.getCreatedAt(),   "createdAt은 null이 아니어야 한다");
        // 생성 시점이 “지금” 기준으로 1초 이내인지 확인
        assertTrue(
                p.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(1)),
                "createdAt이 현재 시각과 거의 일치해야 한다"
        );
    }
}
