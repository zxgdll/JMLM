package com.nm_huazhi.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public final class EncodingWatchFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(EncodingWatchFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.warn(this + ": init");
	}

	@Override
	public void destroy() {
		LOG.warn(this + ": destory");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		LOG.warn(this + ": before chain");
		LOG.warn("request: " + request.getCharacterEncoding());
		LOG.warn("requset: " + request.toString());
		LOG.warn("response: " + response.getCharacterEncoding());
		
		if (null == this.getHttpRequest(request).getSession().getAttribute("user")) {
			LOG.warn("YOU ARE BAD MAN");
		} else {
			LOG.warn("YOU ARE GOOD MAN");
		}
		
		chain.doFilter(request, response);
		LOG.warn(this + ": after chain");
	}
	
	private HttpServletRequest getHttpRequest(ServletRequest request) {
		return (HttpServletRequest) request;
	}
}
