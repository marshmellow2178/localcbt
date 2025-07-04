package com.marshmellow.localcbt.entity;
import jakarta.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@Table(name = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;

	private LocalDateTime createdAt;

	public static Problem create(String question, String answer) {
		Problem problem = new Problem();
		problem.question = question;
		problem.answer = answer;
		problem.createdAt = LocalDateTime.now();
		return new Problem();
	}
	
	public void update(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

}
