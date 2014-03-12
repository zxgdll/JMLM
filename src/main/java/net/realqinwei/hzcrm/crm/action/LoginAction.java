package net.realqinwei.hzcrm.crm.action;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.LoginLog;
import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.TreeComponent;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;
import net.realqinwei.hzcrm.crm.domain.UserRepository;

import net.realqinwei.hzcrm.crm.service.intf.LogService;
import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware, ApplicationAware {
	
	private static final Logger LOG = Logger.getLogger(LoginAction.class);
	//private static final Pattern pattern = Pattern.compile("");
	
	private int loginId;
	private String password;
	
	private UserService service;
	private UserRepository userRepository;
	private TreeRepository treeRepository;
	private LogService logService;
	private TimestampCreator timer;
	private Map<String, Object> session;
	private Map<String, Object> application;
	
	private boolean userIDExist() {
		return this.service.userIDExist(this.loginId);
	}
	
	private boolean isPasswordRight() {
		return this.service.isPasswordRight(this.loginId, this.password);
	}
	
	@SuppressWarnings("unchecked")
	private void loginInit(User user) {
		Map<Integer, User> onlineUsers = (Map<Integer, User>) this.application.get("online");
		if (null == onlineUsers) {
			onlineUsers = new HashMap<Integer, User>();
		}
		onlineUsers.put(user.getId(), user);
		this.application.put("online", onlineUsers);
	}
	
	@Override
	public String execute() throws Exception {
		LOG.debug(this.loginId);
		LOG.debug(this.loginId + " try to login");
		if (this.userIDExist()) {
			if (this.isPasswordRight()) {
				
				User user = this.userRepository.findById(this.loginId);
				//ServletActionContext.getRequest().getSession().setAttribute("user", user);
				this.session.put("user", user);
				
				this.loginInit(user);
				
				TreeComponent<User> tree = this.treeRepository.getTree();
				//ServletActionContext.getRequest().getSession().setAttribute("tree", tree);
				//ServletActionContext.getRequest().getSession().setAttribute("userDAO", this.userRepository);
				this.session.put("tree", tree);
				this.session.put("userDAO", this.userRepository);
				
				SortedSet<User> users = this.treeRepository.getBill();
				//ServletActionContext.getRequest().getSession().setAttribute("users", users);
				this.session.put("users", users);
				
				this.logService.saveLog(new LoginLog(user.getId(), user.getUserName(), timer.getTimestamp()));
				
				return user.getUserType() == 0 ? "admin" : SUCCESS;
			} else {
				LOG.warn("Password is not right");
				return INPUT;
			}
		} else {
			LOG.warn(this.loginId + " does not exist");
			return INPUT;
		}
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

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
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
