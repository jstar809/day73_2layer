package com.kim.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	
	@AfterThrowing(pointcut ="PointCut.aPointcut()", throwing ="exceptObj")
	public void printLogAfterThrowing(JoinPoint jp, Exception exceptObj) {
		String methodname =jp.getSignature().getName();
		Object[] args=jp.getArgs();
		
		System.out.println("수행중인 핵심 메서드 명 = "+ methodname);
		System.out.println("사용중인 인자");
		System.out.println("===========");
		for(Object v : args) {
			System.out.println(v);
		}
		System.out.println("===========");
		
		System.out.println("발생한 예외 :"+exceptObj.getMessage() );
		if(exceptObj instanceof EmptyResultDataAccessException) {
			System.out.println("반환된 결과가 없는 에러입니다.~~로 수정해주세요");
		}
		else {
			System.out.println("알 수 없는 오류입니다");
		}
		
	}
}
