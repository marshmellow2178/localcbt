package com.example.demo.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;

	@Column(name = "midx")
	private int midx;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "ctgr")
	private String ctgr;
	
	@Column(name = "date")
	private LocalDateTime date;

	@Builder
	public Point(int idx, int midx, int point, String detail, String ctgr, LocalDateTime date) {
		super();
		this.idx = idx;
		this.midx = midx;
		this.point = point;
		this.detail = detail;
		this.ctgr = ctgr;
		this.date = date;
	}	
	
}
