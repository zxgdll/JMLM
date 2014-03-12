package com.nm_huazhi.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.realqinwei.hzcrm.crm.been.User;

import org.apache.log4j.Logger;

public final class AdminFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(AdminFilter.class);

	private String path;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.path = config.getInitParameter("path");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (null == user || user.getUserType() != 0) {
			String realPath = req.getContextPath() + path;
			LOG.debug(realPath);
			res.sendRedirect(req.getContextPath() + path);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
