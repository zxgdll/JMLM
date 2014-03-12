package net.realqinwei.hzcrm.crm.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class TimestampFormatted implements TimestampCreator {
	
	private static final DateFormat FROMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Timestamp getTimestamp() {
		return Timestamp.valueOf(FROMATER.format(Calendar.getInstance().getTime()));
	}
}
