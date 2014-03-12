package net.realqinwei.hzcrm.crm.domain;

import java.io.Serializable;

public final class Bill<T> implements Serializable {

	private static final long serialVersionUID = -5465001807242396339L;

	private int billID;
	private T value;
	private int billOUT;
	private int billIN;
	
	public Bill() {
		
	}

	public Bill(int billID, T value, int billIN, int billOUT) {
		this.setBillID(billID);
		this.setValue(value);
		this.setBillIN(billIN);
		this.setBillOUT(billOUT);
	}
	
	public int getBillID() {
		return billID;
	}

	private void setBillID(int billID) {
		this.billID = billID;
	}

	public T getValue() {
		return value;
	}

	private void setValue(T value) {
		this.value = value;
	}

	public int getBillOUT() {
		return billOUT;
	}

	private void setBillOUT(int billOUT) {
		this.billOUT = billOUT;
	}

	public int getBillIN() {
		return billIN;
	}

	private void setBillIN(int billIN) {
		this.billIN = billIN;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		str.append(this.billID).append(", ");
		str.append(this.value).append(", ");
		str.append(this.billIN).append(", ");
		str.append(this.billOUT);
		str.append("]");
		return str.toString();
	}
}
