package net.realqinwei.hzcrm.crm.action;

import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.domain.*;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public final class Tree2AppletAction extends ActionSupport {

	private static final long serialVersionUID = -7269811286039337201L;
	private static final Logger LOG = Logger.getLogger(Tree2AppletAction.class);
	private static final String RESPONSE_CONTENT_TYPE = "application/x-java-serialized-object";

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }

    public void setNodeRepository(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    private NodeRepository nodeRepository;
	private TreeRepository treeRepository;
	
	@Override
	public String execute() throws Exception {
		
		Integer userID = Integer.valueOf(ServletActionContext.getRequest().getParameter("userID"));
		LOG.warn(userID);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(RESPONSE_CONTENT_TYPE);
		ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
		
		TreeComponent<Node> tree = this.treeRepository.getTree();
		LOG.warn(tree.getValue().getUserName());
		
		/**
		People people = new People();
		people.setName("Thomas");
		*/
		
		oos.writeObject(tree.find(this.getNodeRepository().findById(userID)));
		oos.flush();
		oos.close();
		
		return SUCCESS;
	}
	
	public TreeRepository getTreeRepository() {
		return treeRepository;
	}

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}

}
