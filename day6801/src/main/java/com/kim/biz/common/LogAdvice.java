package com.kim.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;


@Service
@Aspect
public class LogAdvice {
	@Before("PointCut.bPointcut()")
	public void printLog2() {
		System.out.println("셀렉트류를 탐색합니다..");
	}
	@Before("PointCut.aPointcut()")
	public void printLog3(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		//현재 수행중인 포인트 컷(핵심로직)의 메서드명
		Object[] args = jp.getArgs();
		// 현재 수행중인 포인트 컷이 사용하는 인자들의 정보
		System.out.println("수행중인 핵심메서드 이름 : "+methodName);
		System.out.println("사용하는 인자 ");
		for(Object v:args) {
			System.out.println(v);
		}
	}
	
}
