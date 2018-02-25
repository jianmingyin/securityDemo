package org.bowman.springboot.securityDemo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.catalina.servlet4preview.GenericFilter;

abstract class MyBaseErrorHandler extends GenericFilter {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(req, res);
		} catch (Exception e) {
			HttpServletResponseWrapper response = (HttpServletResponseWrapper) res;
			handlerException(e, response);
		}
	}

	@Override
	public void destroy() {}
	
	protected abstract void handlerException (Exception e, HttpServletResponseWrapper response)  throws IOException;
}
