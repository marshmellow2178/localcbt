package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberSvc;
import com.example.demo.vo.SignUpVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberCtrl{
	
	private final MemberSvc mSvc;
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String login(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest request,
			RedirectAttributes ra
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		if(m==null) {
			m = mSvc.login(id, pw);
			if(m==null) { 
				ra.addAttribute("error", "회원정보가 일치하지 않습니다");
			}else {
				session.setAttribute("logininfo", m);
			}
		}
		return "redirect:/index";
	}
	
	@RequestMapping(value = {"/logout"})
	public String logout(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}
	
	@RequestMapping(value = {"/signup_form"})
	public String signupForm(
			Model model
			) {
		model.addAttribute("dList", mSvc.getDomainList());
		return "member/sign_up";
	}
	
	@RequestMapping(value = {"/idcheck"})
	public int idCheck(
			@RequestParam("id") String id
			) {
		return mSvc.countById(id);
	}
	
	@RequestMapping(value = {"/sign_up"})
	public String signup(
			SignUpVo suv,
			Model model
			) {
		mSvc.sign_up(suv);
		return "redirect:/index";
	}
}
