package com.kim.biz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 의존관계
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	// init() 통해서 DI
	public void init() throws ServletException {
		handlerMapping=new HandlerMapping();
		viewResolver=new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doAction(request,response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getRequestURI();
		command=command.substring(command.lastIndexOf("/"));
		System.out.println(command);
		/*
		Controller ctrl=handlerMapping.getController(command);
		String viewName=ctrl.handleRequest(request, response);
		
		String view=null;
		if(viewName.contains(".do")) {
			view=viewName;
		}
		else {
			view=viewResolver.getView(viewName);
		}
		response.sendRedirect(view);
		*/
	}

}
