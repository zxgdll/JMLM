package net.realqinwei.hzcrm.crm.service.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.LoginLog;

public interface LogService {
	
	public void saveLog(LoginLog log);
	public List<LoginLog> allLogs();
}
