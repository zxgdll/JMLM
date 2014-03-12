package net.realqinwei.hzcrm.crm.dao.intf;

import net.realqinwei.hzcrm.crm.been.BigUser;

import java.util.List;

public interface BigUserDAO {
	
	public void saveUser(BigUser user);
	public void removeUser(BigUser user);
	public BigUser findUserByID(Integer id);
	public List<BigUser> findAll();
	public void updateUser(BigUser user);

}
