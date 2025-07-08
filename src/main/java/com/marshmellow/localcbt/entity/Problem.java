package com.marshmellow.localcbt.entity;
import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "question", nullable = false)
	private String question;

	@Column(name = "answer", nullable = false)
	private String answer;

	@Column(name="created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	public static Problem create(String question, String answer) {
		Problem problem = new Problem();
		problem.question = question;
		problem.answer = answer;
		return problem;
	}
	
	public void update(String question, String answer) {
		this.question = question;
		this.answer = answer;
		this.modifiedAt = LocalDateTime.now();
	}

}
