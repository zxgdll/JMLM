package net.realqinwei.hzcrm.crm.service.impl;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.LoginLog;
import net.realqinwei.hzcrm.crm.dao.intf.LogDAO;

import net.realqinwei.hzcrm.crm.service.intf.LogService;

public final class LogServiceImpl implements LogService {
	
	private LogDAO logDAO;

	@Override
	public void saveLog(LoginLog log) {
		this.logDAO.save(log);
		
	}

	@Override
	public List<LoginLog> allLogs() {
		return this.logDAO.all();
	}
	
	public LogDAO getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
}
