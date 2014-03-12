package net.realqinwei.hzcrm.crm.domain;

public final class HuazhiFee implements Fee {
	
	private static final long serialVersionUID = -98258799741359523L;
	
	public static final int JOIN_FEE;
	public static final int FIRST_BONUS;
	public static final int SECOND_BONUS;
	public static final int THIRD_BONUS;
	public static final int FOURTH_BONUS;
	public static final int REFER_BONUS;
	public static final int INIT;
	public static final int SUCCESS_BONUS;
	
	/**
	static {
		JOIN_FEE = 19500;
		FIRST_BONUS = 500;
		SECOND_BONUS = 2000;
		THIRD_BONUS = 3000;
		FOURTH_BONUS = 4000;
	}
	*/
	
	static {
		INIT = 0;
		JOIN_FEE = 6500;
		FIRST_BONUS = 200;
		SECOND_BONUS = 600;
		THIRD_BONUS = 800;
		FOURTH_BONUS = 1000;
		REFER_BONUS = 650;
		SUCCESS_BONUS = 33000;
	}
	
	private HuazhiFee() {
		
	}
}
