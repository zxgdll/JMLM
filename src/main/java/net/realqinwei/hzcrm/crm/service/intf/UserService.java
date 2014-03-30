package net.realqinwei.hzcrm.crm.service.intf;

import net.earthcoder.jmlm.domain.Human;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;

import java.util.List;

public interface UserService {

	public boolean userIDExist(Integer userID);
	public boolean isPasswordRight(Integer userID, String password);
	
	public void save(User user);
	public void update(User user);
	
	public List<User> getUsers();
    public List<User> getUsersHaveNodes();
    public User findById(int id);

    public boolean userWithPasswordExists(User user);
}
