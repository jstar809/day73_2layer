package com.kim.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCut {
	@Pointcut("execution(* com.kim.biz..*Impl.*(..))")
	public void aPointcut() {}
	
	@Pointcut("execution(* com.kim.biz..*Impl.select*(..))")
	public void bPointcut() {}
	
}
