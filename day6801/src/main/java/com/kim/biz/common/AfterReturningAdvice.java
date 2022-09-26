package com.kim.biz.common;

import org.aspectj.lang.JoinPoint;

import com.kim.biz.member.MemberVO;

public class AfterReturningAdvice {
	public void printLogAfterReturning(JoinPoint jp, Object returnObj) {
		Object[] args=jp.getArgs();
		
		if(returnObj instanceof MemberVO) {
			MemberVO mvo = (MemberVO)returnObj;
			if(mvo.getRole().equals("ADMIN")){
				System.out.println("������ �Դϴ�.");
			}
			else {
				System.out.println("�Ϲݻ���� �Դϴ�.");
			}
		}
		System.out.println("�ٽ� �޼����� ��ȯ�� : "+returnObj);
	}
}
