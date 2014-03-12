package net.realqinwei.hzcrm.crm.service.intf;

import net.realqinwei.hzcrm.crm.been.BigUser;

import java.util.List;

public interface BigUserService {

	public boolean userIDExist(Integer userID);
	public boolean isPasswordRight(Integer userID, String password);
	
	public void save(BigUser user);
	public void update(BigUser user);
	
	public List<BigUser> getUsers();
}
