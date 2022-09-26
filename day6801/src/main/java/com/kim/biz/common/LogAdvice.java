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
		System.out.println("����Ʈ���� Ž���մϴ�..");
	}
	@Before("PointCut.aPointcut()")
	public void printLog3(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		//���� �������� ����Ʈ ��(�ٽɷ���)�� �޼����
		Object[] args = jp.getArgs();
		// ���� �������� ����Ʈ ���� ����ϴ� ���ڵ��� ����
		System.out.println("�������� �ٽɸ޼��� �̸� : "+methodName);
		System.out.println("����ϴ� ���� ");
		for(Object v:args) {
			System.out.println(v);
		}
	}
	
}
