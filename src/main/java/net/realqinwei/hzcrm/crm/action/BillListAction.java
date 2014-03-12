package net.realqinwei.hzcrm.crm.action;

import java.util.*;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class BillListAction extends ActionSupport {

	private static final long serialVersionUID = -8342867849486128578L;

	private static final Logger LOG = Logger.getLogger(BillListAction.class);
	
	private TreeRepository treeRepository;
	
	@Override
	public String execute() throws Exception {
		
		Integer userID = Integer.valueOf(ServletActionContext.getRequest().getParameter("userID"));
		LOG.debug(userID);
		
		SortedSet<User> users = this.treeRepository.getBill();
		LOG.debug(users.size());
		
		ServletActionContext.getRequest().setAttribute("billUser", userID);

		return SUCCESS;
	}
	
	public TreeRepository getTreeRepository() {
		return treeRepository;
	}

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}
}
