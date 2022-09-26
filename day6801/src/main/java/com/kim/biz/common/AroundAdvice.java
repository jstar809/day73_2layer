package com.kim.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

// around�� ����� advice�� �ݵ�� pjp�� input���� �������Ѵ�!
// ex) ���� ���� Ŭ����

public class AroundAdvice {
	public Object printLogAround(ProceedingJoinPoint pjp) throws Throwable{
		String methodname = pjp.getSignature().getName();
		System.out.println("������ �ٽ� �޼����"+methodname);
		
		StopWatch sw = new StopWatch();
		sw.start();
		Object returnObj=pjp.proceed();//�����ؾ��� ����Ʈ ��
		sw.stop();
		System.out.println("���� �ð�: " +sw.getTotalTimeMillis()+"ms");
		return returnObj;
		
//		System.out.println("[BEFORE]");
//		Object returnObj = pjp.proceed(); //����Ǿ���� ����Ʈ��
//		//pjp.proceed()�� ���� ����Ͻ� �޼��尡 �����!
//		System.out.println("[AFTER]");
//		return returnObj;
	}
}
