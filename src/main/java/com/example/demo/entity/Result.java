package com.example.demo.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicUpdate
@Table(name = "result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "midx")
	private int midx;
	
	@Column(name = "tidx")
	private int tidx;
	
	@Column(name = "correct")
	private int correct;
	
	@Column(name = "pcount")
	private int pcount;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	public void setResult(int correct, int pcount) {
		this.correct = correct;
		this.pcount = pcount;
		this.date = LocalDateTime.now();
	}

	@Builder
	public Result(int idx, int midx, int tidx, int correct, int pcount, LocalDateTime date) {
		super();
		this.idx = idx;
		this.midx = midx;
		this.tidx = tidx;
		this.correct = correct;
		this.date = date;
		this.pcount = pcount;
	}
	
}
