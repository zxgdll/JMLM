package net.realqinwei.hzcrm.crm.dao.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.LoginLog;

public interface LogDAO {
	
	public void save(LoginLog log);
	public List<LoginLog> all();
}
