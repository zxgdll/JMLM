package net.realqinwei.hzcrm.crm.domain;

import net.realqinwei.hzcrm.crm.been.User;

public interface BillRepository {
	
	public void saveBill(Bill<User> bill);
}
