package com.kim.biz.controller;

public class ViewResolver {
	public String prefix;	//의존관계 -> DI => setter 주입
	public String suffix;	//의존관계 -> DI => setter 주입
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix+viewName+suffix;
	}
}
