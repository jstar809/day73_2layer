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
		
		System.out.println("�������� �ٽ� �޼��� �� = "+ methodname);
		System.out.println("������� ����");
		System.out.println("===========");
		for(Object v : args) {
			System.out.println(v);
		}
		System.out.println("===========");
		
		System.out.println("�߻��� ���� :"+exceptObj.getMessage() );
		if(exceptObj instanceof EmptyResultDataAccessException) {
			System.out.println("��ȯ�� ����� ���� �����Դϴ�.~~�� �������ּ���");
		}
		else {
			System.out.println("�� �� ���� �����Դϴ�");
		}
		
	}
}
