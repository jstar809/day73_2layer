package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

//@Controller

public class LoginController {
	
	//@RequestMapping("/login.do")
	public void selectOneMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		MemberVO mVO = new MemberVO();
		mVO.setMid(mid);
		mVO.setMpw(mpw);

		MemberDAO mDAO = new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		System.out.println(mVO);
	      
	      ModelAndView mav=new ModelAndView();
	      
		if (mVO == null) {  //�̸� ������־ String viewName�� ������ String�� ��ȯ
			mav.setViewName("redirect:login.jsp");
		} else {
			 HttpSession session=request.getSession();
		      session.setAttribute("member", mVO);
		      mav.setViewName("redirect:main.do");
		}

	}

//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String mid = request.getParameter("mid");
//		String mpw = request.getParameter("mpw");
//		
//		MemberVO mVO = new MemberVO();
//		mVO.setMid(mid);
//		mVO.setMpw(mpw);
//
//		MemberDAO mDAO = new MemberDAO();
//		mVO=mDAO.selectOneMember(mVO);
//		System.out.println(mVO);
//	      
//	      ModelAndView mav=new ModelAndView();
//	      
//		if (mVO == null) {  //�̸� ������־ String viewName�� ������ String�� ��ȯ
//			mav.setViewName("redirect:login.jsp");
//		} else {
//			 HttpSession session=request.getSession();
//		      session.setAttribute("member", mVO);
//		      mav.setViewName("redirect:main.do");
//		}
//		return mav;
//
//	}
	}
	
	
	
//	@Override
//	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
//		String mid = request.getParameter("mid");
//		String mpw = request.getParameter("mpw");
//		
//		MemberVO mVO = new MemberVO();
//		mVO.setMid(mid);
//		mVO.setMpw(mpw);
//
//		MemberDAO mDAO = new MemberDAO();
//		mVO=mDAO.selectOneMember(mVO);
//		System.out.println(mVO);
//		 HttpSession session=request.getSession();
//	      session.setAttribute("login", mVO.getName());
//	      
//		if (mVO == null) {  //�̸� ������־ String viewName�� ������ String�� ��ȯ
//			return "login"; // VR�� .jsp�� �߰��ϱ� ������ �����ؼ� ��ȯ���ָ� �ȴ�.
//		} else {
//			return "main.do";
//		}
//
//	}


