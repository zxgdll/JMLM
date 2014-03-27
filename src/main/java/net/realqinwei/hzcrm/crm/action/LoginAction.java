package net.realqinwei.hzcrm.crm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.opensymphony.xwork2.ActionSupport;

import net.earthcoder.jmlm.domain.BinaryTree;
import net.realqinwei.hzcrm.crm.been.LoginLog;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.NodeRepository;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;

import net.realqinwei.hzcrm.crm.service.intf.LogService;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware, ApplicationAware {

    private static final long serialVersionUID = 1918926563861586309L;
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    private static final String USER_TYPE_ADMIN = "admin";

	private int loginId;
	private String password;
    private NodeService nodeService;
	private NodeRepository nodeRepository;
	private TreeRepository treeRepository;
	private LogService logService;
	private TimestampCreator timer;
	private Map<String, Object> session;
	private Map<String, Object> application;
    private UserService userService;

	private boolean userIDExist() {
		return this.getUserService().userIDExist(this.getLoginId());
	}
	
	private boolean isPasswordRight() {
		return this.getUserService().isPasswordRight(this.getLoginId(), this.getPassword());
	}

	@SuppressWarnings("unchecked")
	private void loginInit(User user) {
		Map<Integer, User> onlineUsers = (Map<Integer, User>) application.get("online");
		if (null == onlineUsers) {
			onlineUsers = new HashMap<Integer, User>();
            onlineUsers.put(user.getId(), user);
            application.put("online", onlineUsers);
		}
	}
	
	@Override
	public String execute() throws Exception {
		LOG.debug(loginId + " try to login");
		if (userIDExist()) {
			if (isPasswordRight()) {

                User loginUser = getUserService().findById(getLoginId());
				session.put("user", loginUser);
				loginInit(loginUser);

                List<User> allUsers = getUserService().getUsers();
                LOG.debug(allUsers.size());
                session.put("allUsers", allUsers);

                session.put("service", getNodeService());
				
				BinaryTree tree = getTreeRepository().rebuild();
                tree.printNode();
                tree.printBill();
				session.put("tree", tree);
				session.put("userDAO", getNodeRepository());
				
				SortedSet<Node> users = getTreeRepository().getBinaryBill();
				session.put("users", users);
				
				getLogService().saveLog(new LoginLog(loginUser.getId(), loginUser.getUserName(), timer.getTimestamp()));
				
				return loginUser.getUserType() == 0 ? LoginAction.USER_TYPE_ADMIN : SUCCESS;
			} else {
				LOG.warn("Password is not right");
				return INPUT;
			}
		} else {
			LOG.warn(loginId + " does not exist");
			return INPUT;
		}
	}

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
