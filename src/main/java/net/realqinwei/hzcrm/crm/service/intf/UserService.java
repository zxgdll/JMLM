package net.realqinwei.hzcrm.crm.service.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.User;

public interface UserService {

	public boolean userIDExist(Integer userID);
	public boolean isPasswordRight(Integer userID, String password);
	
	public void save(User user);
	public void update(User user);
	
	public List<User> getUsers();
}
