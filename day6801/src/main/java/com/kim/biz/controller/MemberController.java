package com.kim.biz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

@Controller
@SessionAttributes("member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String index() {
		return "login.jsp";
	}
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(MemberVO mVO,HttpSession session) {
		System.out.println("됫나?");
		System.out.println("log in실페하면 mvo가 어떻게 되지?"+mVO);
		mVO=memberService.selectOneMember(mVO);
		if(mVO==null) {
			
			return "redirect:login.do";
		}
		else {
			session.setAttribute("user", mVO);
			return "redirect:main.do";
		}
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.do";
	}
	@RequestMapping(value="/mypage.do")
	public String mypage(HttpSession session,Model model) {
		model.addAttribute("member",session.getAttribute("user"));
		return "mypage.jsp";
	}
	@RequestMapping("/signin.do")
	public String signin(MemberVO mVO) {
		memberService.insertMember(mVO);
		return "redirect:login.do";
	}
	@RequestMapping("/updateMember.do")
	public String updateMember(HttpSession session,@ModelAttribute("member")MemberVO mVO) {
		System.out.println("업데이트 데이터확인: "+mVO);
		memberService.updateMember(mVO);
		session.invalidate();
		return "redirect:login.do";
	}
	@RequestMapping("/deleteMember.do")
	public String deleteMember(HttpSession session,@ModelAttribute("member")MemberVO mVO) {
		System.out.println("딜리트 데이터 확인"+mVO);
		memberService.deleteMember(mVO);
		session.invalidate();
		return "redirect:login.do";
	}
}
