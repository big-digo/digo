package com.cetc.bigdata.analysis.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * 禁止页面缓存：避免注销后，输入指定的URL，进入注销前访问的页面
 * 
 * @author Qu Gang
 *
 */
public class NoCacheFilter extends HttpServlet implements Filter {
	
	private static final long serialVersionUID = 1L;
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
		try {
			// request.setCharacterEncoding("GB2312");//设置编码
			((HttpServletResponse) response).setHeader("Pragma", "No-cache");
			((HttpServletResponse) response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse) response).setHeader("Expires", "0");// 禁止缓存
			filterChain.doFilter(request, response);
		} catch (ServletException sx) {
			filterConfig.getServletContext().log(sx.getMessage());
		} catch (IOException iox) {
			filterConfig.getServletContext().log(iox.getMessage());
		}
	}

	public void destroy() {
	}
}
