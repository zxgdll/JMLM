package net.realqinwei.hzcrm.crm.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.User;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;

public class LogoffAction extends ActionSupport implements ApplicationAware {
	
	private static final long serialVersionUID = 2631180091700482230L;

	private static final Logger LOG = Logger.getLogger(LogoffAction.class);
	
	private Map<String, Object> application;
	
	@SuppressWarnings("unchecked")
	private void logOff(User user) {
		Map<Integer, User> onlineUsers = (Map<Integer, User>) this.application.get("online");
		if (null != onlineUsers) {
			onlineUsers.remove(user.getId());
			this.application.put("online", onlineUsers);
			LOG.warn(onlineUsers.size());
		}
	}
	
	@Override
	public String execute() throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		
		this.logOff(user);
		
		session.invalidate();
		LOG.debug("Logoff");
		return LOGIN;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
