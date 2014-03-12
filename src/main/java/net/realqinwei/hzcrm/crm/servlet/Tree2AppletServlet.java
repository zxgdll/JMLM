package net.realqinwei.hzcrm.crm.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.TreeComponent;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Tree2AppletServlet extends HttpServlet {

	private static final long serialVersionUID = 5000831958801349006L;
	private static final Logger LOG = Logger.getLogger(Tree2AppletServlet.class);
	private static final String RESPONSE_CONTENT_TYPE = "application/x-java-serialized-object";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(RESPONSE_CONTENT_TYPE);
		ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
		
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		TreeRepository treeRepository = (TreeRepository) wac.getBean("TreeRepositoryBean");
		TreeComponent<User> tree = treeRepository.getTree();
		LOG.warn(tree.getValue().getUserName());
		
		oos.writeObject(tree);
		oos.flush();
		oos.close();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
}
// ~