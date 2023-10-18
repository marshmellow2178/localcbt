package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Point;
import com.example.demo.repository.PointRepo;
import com.example.demo.vo.Pageinfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PointSvc {
	private final PointRepo pRepo;
	
	public List<Point> getPointList(int midx, Pageable pageable, Pageinfo pageinfo){
		Page<Point> p = pRepo.findByMidx(midx, pageable);
		pageinfo.setPage(p);
		return p.getContent();
	}
	
	
}
