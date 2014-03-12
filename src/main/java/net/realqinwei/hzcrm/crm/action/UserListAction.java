package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.TreeComponent;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;
import net.realqinwei.hzcrm.crm.domain.UserRepository;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class UserListAction extends ActionSupport {
	
	private static final long serialVersionUID = -2204642984377914302L;

	private static final Logger LOG = Logger.getLogger(UserListAction.class);
	
	private UserRepository userRepository;
	private TreeRepository treeRepository;
	
	@Override
	public String execute() throws Exception {
		Integer userID = Integer.valueOf(ServletActionContext.getRequest().getParameter("userID"));
		LOG.debug(userID);
		
		TreeComponent<User> tree = this.treeRepository.getTree();
		
		ServletActionContext.getRequest().setAttribute("leafs", tree.getLeafs(this.userRepository.findById(userID)));
		ServletActionContext.getRequest().setAttribute("userID", userID);
		return SUCCESS;
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public TreeRepository getTreeRepository() {
		return treeRepository;
	}

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}
}
