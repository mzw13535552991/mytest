package com.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.bean.Member;
import com.bean.Sysuser;


import java.io.IOException;

public class UrlFilter implements Filter {
	public FilterConfig config;

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
		// System.out.println("----------------------->过滤器被创建");
	}

	public static boolean isContains(String container, String[] regx) {
		boolean result = false;

		for (int i = 0; i < regx.length; i++) {
			if (container.indexOf(regx[i]) != -1) {
				return true;
			}
		}
		return result;
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(
				(HttpServletResponse) res);
		String requestURI = req.getRequestURI();
		//System.out.println("--------------------->过滤器：请求地址" + requestURI);

		String logonStrings = config.getInitParameter("logonStrings");// -登录登陆页面

		String[] logonList = logonStrings.split(";");
		//String[] includeList = includeStrings.split(";");

		Sysuser admin = (Sysuser) req.getSession().getAttribute("admin");
		Member member = (Member) req.getSession().getAttribute("sessionmember");
		
		//仅仅对指定过滤參数后缀进行过滤
		
		if (!this.isContains(req.getRequestURI(), logonList)) {
			
			
			if(requestURI.contains("admin")&&admin==null){
					res.sendRedirect("/onliinestudyssm/admin/login.jsp");
					return;
			}
			if(!requestURI.contains("admin")&&member==null){
				//res.sendRedirect("qtindex.do");
				if(requestURI.contains("tzAdd.do")||requestURI.contains("pinlunAdd.do")||requestURI.contains("favAdd.do")||requestURI.contains("tzhfAdd.do")||requestURI.contains("tzhfLb.do")||requestURI.contains("messageAdd.do")
){
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}else{
					req.getRequestDispatcher("index.do").forward(req, res);
				}
				return;
			}
		}
			filterChain.doFilter(req, res);

	}

	public void destroy() {
		this.config = null;
		//System.out.println("----------------------->过滤器被销毁");
	}

}
