package net.realqinwei.hzcrm.crm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.TreeComponent;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TreeServlet extends HttpServlet {

	private static final long serialVersionUID = -8159057704190460477L;

	public TreeServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>test.html</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/nm-huazhi/test.css\">");
		out.println("</head>");
		out.println("<body><div class=\"tree\">");
		
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		TreeRepository treeRepository = (TreeRepository) wac.getBean("TreeRepositoryBean");
		TreeComponent<User> tree = treeRepository.getTree();
		
		out.println("<ul>");
		out.println(getContent(tree));
		out.println("</ul>");

		out.flush();
		out.close();
	}
	
	private static String getContent(TreeComponent<User> tree) {
		StringBuilder content = new StringBuilder();

		content.append("<li>");
		content.append("<a href=\"#\">").append(tree.getValue().getId()).append("</a>");
		if (null != tree.getChilds()) {
			content.append("<ul>");
			for (TreeComponent<User> t : tree.getChilds()) {
				content.append(getContent(t));
			}
			content.append("</ul>");
		}
		content.append("</li>");

		return content.toString();
	}

	public void init() throws ServletException {

	}
}//