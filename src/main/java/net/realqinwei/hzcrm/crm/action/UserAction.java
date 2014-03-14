package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.MD5;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -9183608064713571507L;
	private static final Integer CREATE_USER_TYPE = 1;
	private static final Logger LOG = Logger.getLogger(UserAction.class);

    private TimestampCreator timer;
    private UserService userService;
    private User user;
    private String oldPassword;
    private String newPassword;
    private String newPasswordAgain;

	public String addUser() {
        /**
         * 将新添加的用户加入排列
		TreeComponent<User> tree = this.getTreeRepository().getTree();
		
		this.setList(new ArrayList<User>());
		for (User u: this.getUserRepository().findAll()) {
			TreeComponent<User> tmp = tree.find(u);
			if (tmp.getState().equals(tmp.successState())) {
				;
			} else {
				this.list.add(u);
			}
		}
		*/
		return SUCCESS;
	}
	
	public String save() {
		this.user.setUserPassword(MD5.getMD5Str(this.user.getUserIDCard().substring(
				this.user.getUserIDCard().length() - 6,
				this.user.getUserIDCard().length())));
		this.user.setUserType(UserAction.CREATE_USER_TYPE);
		this.user.setUserCreateTime(this.getTimer().getTimestamp());
		this.getUserService().save(this.user);
		return SUCCESS;
	}
	
	public String saveEdit() {
		this.getUserService().update(this.getUser());
		return SUCCESS;
	}
	
	public String edit() {
        this.setUser(this.getUserService().findById(Integer.valueOf(ServletActionContext.getRequest().getParameter("userID"))));
		return null == this.getUser() ? ERROR : SUCCESS;
	}
	
	public String modify() {
		if (this.newPassword.equals(this.newPasswordAgain)) {
			this.user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			if (this.user.getUserPassword().equals(MD5.getMD5Str(this.oldPassword))) {
				this.user.setUserPassword(MD5.getMD5Str(this.newPassword));
				this.getUserService().update(this.user);
				ServletActionContext.getRequest().getSession().setAttribute("user", this.user);
				return SUCCESS;
			} else {
				LOG.warn("You input a wrong password.");
				LOG.warn(this.user.getUserPassword() + " | " + MD5.getMD5Str(this.oldPassword));
				return INPUT;
			}
		} else {
			LOG.warn("New passwords you input were not equals.");
			return INPUT;
		}
	}

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}

	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}

    public TimestampCreator getTimer() {
        return timer;
    }

    public void setTimer(TimestampCreator timer) {
        this.timer = timer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
