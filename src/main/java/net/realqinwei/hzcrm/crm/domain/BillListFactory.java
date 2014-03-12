package net.realqinwei.hzcrm.crm.domain;

import java.util.*;

public final class BillListFactory<T> {
	
	private static final BillListFactory SELF_INSTANCE = new BillListFactory();
	
	private static final int BILL_ID_INIT = 1;

	private BillListFactory() {
		
	}
	
	public static BillListFactory getInstance() {
		return SELF_INSTANCE;
	}
	
	public List<Bill<T>> getBillList(TreeComponent<T> newTree) {
		return newTree.getBillList();
	}
}
