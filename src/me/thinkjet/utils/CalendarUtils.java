package me.thinkjet.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName CalendarUtils
 * @author johnny_zyc
 * @Modified 2013-3-2 下午2:49:12
 * 
 */
public final class CalendarUtils {

	public static final String[] WEEKDAYS = new String[] { "UNDEFINED",
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	private CalendarUtils() {
		// nothing to do
	}

	public static int getCurrentDayOfWeek() {
		return getCurrentDayOfWeekFor(new Date());
	}

	public static int getCurrentDayOfWeekFor(final Date date) {
		return getCalendarFor(date).get(Calendar.DAY_OF_WEEK);
	}

	public static Calendar getCalendarFor(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
