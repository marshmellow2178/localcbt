package com.example.demo.vo;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class Pageinfo {
	private int cpage, psize, bsize, pcnt, spage; 
	private long rcnt;
	//현재페이지, 페이지당 레코드수, 블록당 페이지수, 전체레코드수, 페이지수, 블록 시작페이지
	
	public <T> void setPage(Page<T> page) {
		this.cpage = page.getNumber()+1;
		this.psize = page.getSize();
		this.bsize = 10;
		this.pcnt = page.getTotalPages();
		this.rcnt = page.getTotalElements();
		this.spage = ((cpage -1)/bsize * bsize+1);
	}
	
	/*
	 * 페이지네이션
	 * 블록 시작페이지 ~ min(블록 시작페이지 + 10 -1, 페이지수) 까지 루프
	 * 현재 페이지 = 루프 숫자일경우 강조
	 * 페이지수가 1이 아니라면 처음/이전 링크 활성화
	 * 페이지수가 마지막 페이지가 아니라면 마지막/다음 링크 활성화
	 * 
	 * if(cpage != 1){ a href = 1 }
	 * else { a href = # }
	 * 
	 * for(int i = spage, i<spage+10;i++){
	 * 	if(cpage==i) { 
	 * 		addClass(active)
	 * 		a href = # 
	 * 	}else{
	 * 		a href = i
	 * 	}
	 * }
	 * 
	 * if(cpage != pcnt){
	 * 		a href = pcnt
	 * 	}else{ 
	 * 		a href = #
	 * }
	 */
}
