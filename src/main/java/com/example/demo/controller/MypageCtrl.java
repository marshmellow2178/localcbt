package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.BoardFree;
import com.example.demo.entity.Domain;
import com.example.demo.entity.FreeReply;
import com.example.demo.entity.Member;
import com.example.demo.entity.Point;
import com.example.demo.service.BoardSvc;
import com.example.demo.service.MemberSvc;
import com.example.demo.service.PointSvc;
import com.example.demo.service.TestSvc;
import com.example.demo.vo.MemberUpdateVo;
import com.example.demo.vo.Pageinfo;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageCtrl{
	
	private final MemberSvc mSvc;
	private final PointSvc pSvc;
	private final BoardSvc bSvc;
	private final TestSvc tSvc;
	
	@RequestMapping("")
	public String myPage(
			HttpServletRequest request,
			Model model
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logininfo")==null) {
			return "redirect:/localcbt/index";
		}
		return "member/mypage";
	}
	
	@RequestMapping("/update_form")
	public String memberUpdateForm(
			HttpServletRequest request,
			Model model
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logininfo")==null) {
			return "redirect:/localcbt/index";
		}
		List<Domain> dList= mSvc.getDomainList();
		model.addAttribute("dList", dList);
		return "member/member_up";
	}
	
	@RequestMapping("/update_proc")
	public String memberUpdate(
			MemberUpdateVo muv,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		mSvc.memberUpdate(muv, m);
		return "redirect:/localcbt/mypage/update_form";
	}
	
	@RequestMapping("/point")
	public String getPointList(
			@PageableDefault(size=10, sort="date", direction = Sort.Direction.DESC) Pageable pageable,
			HttpServletRequest request,
			Model model
			) {
		Pageinfo pageInfo = new Pageinfo();
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		if(m==null) {
			return "redirect:/localcbt/index";
		}

		List<Point> pList = pSvc.getPointList(m.getIdx(), pageable, pageInfo);
		model.addAttribute("pageinfo", pageInfo);
		model.addAttribute("pList", pList);
		return "member/mypoint";
	}
	
	@RequestMapping("/board")
	public String myBoard(
			@PageableDefault(size=10, sort="date", direction = Sort.Direction.DESC) Pageable pageable,
			HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		if(m==null) {
			return "redirect:/localcbt/index";
		}
		Pageinfo pageInfo = new Pageinfo();
		List<BoardFree> bfList = bSvc.findBoard(m.getIdx(), pageable, pageInfo);
		model.addAttribute("bfList", bfList);
		return "member/myboard";
	}
	
	@RequestMapping("/reply")
	public String myReply(
			@PageableDefault(size=10, sort="date", direction = Sort.Direction.DESC) Pageable pageable,
			HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		if(m==null) {
			return "redirect:/localcbt/index";
		}
		Pageinfo pageInfo = new Pageinfo();
		List<FreeReply> frList = bSvc.findReply(m.getIdx(), pageable, pageInfo);
		model.addAttribute("frList", frList);
		return "member/myreply";
	}
	
	@RequestMapping("/score")
	public String getScoreList(
			HttpServletRequest request,
			Model model
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		model.addAttribute("results", tSvc.getResultList(m.getIdx()));
		model.addAttribute("testinfo", tSvc.getTestList());
		return "member/scores";
	}
}
