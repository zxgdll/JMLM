package net.realqinwei.hzcrm.crm.dao.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.User;

public interface BigUserDAO {
	
	public void saveUser(User user);
	public void removeUser(User user);
	public User findUserByID(Integer id);
	public List<User> findAll();
	public void updateUser(User user);

}
