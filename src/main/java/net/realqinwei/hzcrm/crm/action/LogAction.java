package net.realqinwei.hzcrm.crm.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.LoginLog;
import net.realqinwei.hzcrm.crm.service.intf.LogService;

public class LogAction extends ActionSupport {

	private static final long serialVersionUID = -3177489582412548687L;
	
	private LogService logService;
	private List<LoginLog> logs;

	@Override
	public String execute() throws Exception {
		this.logs = this.logService.allLogs();
		// ServletActionContext.getRequest().setAttribute("logs", this.logService.allLogs());
		return SUCCESS;
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public List<LoginLog> getLogs() {
		return logs;
	}

	public void setLogs(List<LoginLog> logs) {
		this.logs = logs;
	}
}
