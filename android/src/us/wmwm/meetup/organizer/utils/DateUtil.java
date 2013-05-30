package us.wmwm.meetup.organizer.utils;

import java.util.Calendar;

public class DateUtil {
	
	public static Calendar clearTime(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

}
