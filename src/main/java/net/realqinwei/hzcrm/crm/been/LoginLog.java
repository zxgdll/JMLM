package net.realqinwei.hzcrm.crm.been;

import java.io.Serializable;
import java.sql.Timestamp;

public final class LoginLog implements Serializable {

	private static final long serialVersionUID = -5246008785424846098L;
	
	private Integer id;
	private Integer userId;
	private String userName;
	private Timestamp loginTime;
	
	public LoginLog() {
		
	}
	
	public LoginLog(Integer userId, String userName, Timestamp loginTime) {
		this.userId = userId;
		this.userName = userName;
		this.loginTime = loginTime;
	}
	
	private String objectID() {
		StringBuilder str = new StringBuilder();
		str.append(this.id).append(this.userId).append(this.loginTime);
		return str.toString();
	}
	
	@Override
	public int hashCode() {
		return this.objectID().hashCode();
	}
	
	@Override
	public String toString() {
		return this.objectID();
	}

	@Override
	public boolean equals(Object anotherObject) {
		if (null == anotherObject) {
			return false;
		} else if (this == anotherObject) {
			return true;
		} else {
			LoginLog anotherUser = (LoginLog) anotherObject;
			return anotherUser.objectID().equals(this.objectID()) ? true : false;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}


}
