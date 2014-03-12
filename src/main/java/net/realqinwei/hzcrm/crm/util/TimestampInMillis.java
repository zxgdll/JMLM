package net.realqinwei.hzcrm.crm.util;

import java.sql.Timestamp;
import java.util.Calendar;

public final class TimestampInMillis implements TimestampCreator {
	
	@Override
	public Timestamp getTimestamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}
}
