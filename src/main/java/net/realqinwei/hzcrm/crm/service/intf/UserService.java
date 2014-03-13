package net.realqinwei.hzcrm.crm.service.intf;

import net.realqinwei.hzcrm.crm.been.User;

import java.util.List;

public interface UserService {

	public boolean userIDExist(Integer userID);
	public boolean isPasswordRight(Integer userID, String password);
	
	public void save(User user);
	public void update(User user);
	
	public List<User> getUsers();
    public User findById(int id);
}
