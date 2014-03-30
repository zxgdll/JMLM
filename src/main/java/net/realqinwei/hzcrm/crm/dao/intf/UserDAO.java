package net.realqinwei.hzcrm.crm.dao.intf;

import net.earthcoder.jmlm.domain.Human;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;

import java.util.List;

public interface UserDAO {
	
	public void saveUser(User user);
	public void removeUser(User user);
	public User findUserByID(Integer id);
	public List<User> findAll();
	public void updateUser(User user);
    public List<User> getUsersHaveNodes();
    public boolean userWithPasswordExists(User user);


}
