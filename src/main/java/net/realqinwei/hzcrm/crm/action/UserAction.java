package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.domain.NodeRepository;
import net.realqinwei.hzcrm.crm.service.intf.BigUserService;
import net.realqinwei.hzcrm.crm.util.MD5;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -9183608064713571507L;
	private static final Integer CREATE_USER_TYPE = 1;
	private static final Logger LOG = Logger.getLogger(UserAction.class);

    private TimestampCreator timer;
    private BigUserService bigUserService;
    private BigUser user;
    private String oldPassword;
    private String newPassword;
    private String newPasswordAgain;

	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}

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
		this.user.setUserType(CREATE_USER_TYPE);
		this.user.setUserCreateTime(this.timer.getTimestamp());
		this.getBigUserService().save(this.user);
		return SUCCESS;
	}
	
	public String saveEdit() {
		this.getBigUserService().update(this.user);
		return SUCCESS;
	}
	
	public String edit() {
        /*
		this.user = this.getBigUserService()..findById(
				Integer.valueOf(ServletActionContext.getRequest().getParameter("userID")));
				*/
		return null == this.user ? ERROR : SUCCESS;
	}
	
	public String modify() {
		if (this.newPassword.equals(this.newPasswordAgain)) {
			this.user = (BigUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			if (this.user.getUserPassword().equals(MD5.getMD5Str(this.oldPassword))) {
				this.user.setUserPassword(MD5.getMD5Str(this.newPassword));
				this.getBigUserService().update(this.user);
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

    public BigUserService getBigUserService() {
        return bigUserService;
    }

    public void setBigUserService(BigUserService bigUserService) {
        this.bigUserService = bigUserService;
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
}
