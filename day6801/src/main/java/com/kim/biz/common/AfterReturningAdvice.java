package com.kim.biz.common;

import org.aspectj.lang.JoinPoint;

import com.kim.biz.member.MemberVO;

public class AfterReturningAdvice {
	public void printLogAfterReturning(JoinPoint jp, Object returnObj) {
		Object[] args=jp.getArgs();
		
		if(returnObj instanceof MemberVO) {
			MemberVO mvo = (MemberVO)returnObj;
			if(mvo.getRole().equals("ADMIN")){
				System.out.println("관리자 입니다.");
			}
			else {
				System.out.println("일반사용자 입니다.");
			}
		}
		System.out.println("핵심 메서드의 반환값 : "+returnObj);
	}
}
