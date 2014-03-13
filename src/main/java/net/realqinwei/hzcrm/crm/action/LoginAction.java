package net.realqinwei.hzcrm.crm.action;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.LoginLog;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.domain.NodeRepository;
import net.realqinwei.hzcrm.crm.domain.TreeComponent;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;

import net.realqinwei.hzcrm.crm.service.intf.LogService;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware, ApplicationAware {
	
	private static final Logger LOG = Logger.getLogger(LoginAction.class);
	//private static final Pattern pattern = Pattern.compile("");
	
	private int loginId;
	private String password;
    private NodeService nodeService;
	private NodeRepository nodeRepository;
	private TreeRepository treeRepository;
	private LogService logService;
	private TimestampCreator timer;
	private Map<String, Object> session;
	private Map<String, Object> application;
	
	private boolean userIDExist() {
		return this.getNodeService().nodeIDExist(this.loginId);
	}
	
	private boolean isPasswordRight() {
		return this.getNodeService().isPasswordRight(this.loginId, this.password);
	}
	
	@SuppressWarnings("unchecked")
	private void loginInit(Node node) {
		Map<Integer, Node> onlineUsers = (Map<Integer, Node>) this.application.get("online");
		if (null == onlineUsers) {
			onlineUsers = new HashMap<Integer, Node>();
		}
		onlineUsers.put(node.getId(), node);
		this.application.put("online", onlineUsers);
	}
	
	@Override
	public String execute() throws Exception {
		LOG.debug(this.loginId);
		LOG.debug(this.loginId + " try to login");
		if (this.userIDExist()) {
			if (this.isPasswordRight()) {
				
				Node node = this.getNodeRepository().findById(this.loginId);
				//ServletActionContext.getRequest().getSession().setAttribute("user", user);
				this.session.put("user", node);
				
				this.loginInit(node);
				
				TreeComponent<Node> tree = this.treeRepository.getTree();
				//ServletActionContext.getRequest().getSession().setAttribute("tree", tree);
				//ServletActionContext.getRequest().getSession().setAttribute("userDAO", this.userRepository);
				this.session.put("tree", tree);
				this.session.put("userDAO", this.getNodeRepository());
				
				SortedSet<Node> users = this.treeRepository.getBill();
				//ServletActionContext.getRequest().getSession().setAttribute("users", users);
				this.session.put("users", users);
				
				this.logService.saveLog(new LoginLog(node.getId(), node.getUserName(), timer.getTimestamp()));
				
				return node.getUserType() == 0 ? "admin" : SUCCESS;
			} else {
				LOG.warn("Password is not right");
				return INPUT;
			}
		} else {
			LOG.warn(this.loginId + " does not exist");
			return INPUT;
		}
	}

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }

    public void setNodeRepository(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }
	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	//@RequiredStringValidator(message="password", key="message.error.password.required", trim=true)
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public TreeRepository getTreeRepository() {
		return treeRepository;
	}

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}
	
	private static final long serialVersionUID = 1918926563861586309L;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
