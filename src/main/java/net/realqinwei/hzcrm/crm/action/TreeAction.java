package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.domain.TreeRepository;

import org.apache.log4j.Logger;

public class TreeAction extends ActionSupport {
	
	private static final long serialVersionUID = 7251546017908789932L;

	private static final Logger LOG = Logger.getLogger(TreeAction.class);
	
	private TreeRepository treeRepository;
	
	public TreeRepository getTreeRepository() {
		return this.treeRepository;
	}

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}
	
	public String show() {
		LOG.debug("show tree");
		return SUCCESS;
	}
}
