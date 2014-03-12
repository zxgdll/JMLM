package net.realqinwei.hzcrm.crm.service.impl;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.dao.intf.UserDAO;
import net.realqinwei.hzcrm.crm.domain.*;

public final class UserRepositoryImpl implements UserRepository {
	
	private UserDAO userDAO;

	@Override
	public List<User> findAll() {
		return this.userDAO.findAll();
	}

	@Override
	public User findById(int id) {
		return this.userDAO.findUserByID(id);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
