package net.realqinwei.hzcrm.crm.domain;

import net.realqinwei.hzcrm.crm.been.Node;

public interface BillRepository {
	
	public void saveBill(Bill<Node> bill);
}
