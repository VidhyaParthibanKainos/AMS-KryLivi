/**
 * 
 */
package com.workday.custom.int006;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author john.smail
 *
 */
public class DateUtils {

	private static SimpleDateFormat xmlDateWithZoneFormat = new SimpleDateFormat("yyyy-MM-ddXXX");
	private static SimpleDateFormat xmlDateTimeWithZoneFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	public DateUtils() {}
	
	//createDate methods accept months as 1-based instead of 0-based
	
	public static Date now() {
		return Calendar.getInstance().getTime();
	}
	
	public static Date createDate(int year, int month, int day) {
		return createDate(year, month, day, 0, 0, 0, 0, TimeZone.getDefault());
	}
	
	public static Date createDate(int year, int month, int day, String timezone) {
		return createDate(year, month, day, 0, 0, 0, 0, TimeZone.getTimeZone(timezone));
	}
	
	public static Date createDate(int year, int month, int day, TimeZone tz) {
		return createDate(year, month, day, 0, 0, 0, 0, tz);
	}
	
	public static Date createDate(int year, int month, int day, int hour, int minute, int second, int milliseconds) {
		return createDate(year, month, day, hour, minute, second, milliseconds, TimeZone.getDefault());
	}

	public static Date createDate(int year, int month, int day, int hour, int minute, int second, int milliseconds, String timezone) {
		return createDate(year, month, day, hour, minute, second, milliseconds, TimeZone.getTimeZone(timezone));
	}

	public static Date createDate(int year, int month, int day, int hour, int minute, int second, int milliseconds, TimeZone tz) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(tz);

		cal.set(year, month - 1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, milliseconds);
		
		return cal.getTime();
	}
	
	public static Date convertXmlStringToDate(String dateAsXmlString) throws ParseException {
		return xmlDateWithZoneFormat.parse(dateAsXmlString);
	}
	
	public static String convertDateToXmlString(Date dateAsDate) {
		return xmlDateWithZoneFormat.format(dateAsDate);
	}
	
	public static Date convertXmlStringToDateTime(String datetimeAsXmlString) throws ParseException {
		return xmlDateTimeWithZoneFormat.parse(datetimeAsXmlString);
	}
	
	public static String convertDateTimeToXmlString(Date datetimeAsDate) {
		return xmlDateTimeWithZoneFormat.format(datetimeAsDate);
	}
	
	public static Date addDaysToDate(Date originalDate, int daysToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(originalDate);
		cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
		
		return cal.getTime();
	}
	
	public static Date addMonthsToDate(Date originalDate, int monthsToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(originalDate);
		cal.add(Calendar.MONTH, monthsToAdd);
		
		return cal.getTime();
	}
	
	public static Date addSecondsToDate(Date originalDate, int secondsToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(originalDate);
		cal.add(Calendar.SECOND, secondsToAdd);
		
		return cal.getTime();
	}
	
	public static boolean isDateAfter(Date originalDate, Date comparisonDate) {
		if (originalDate == null && comparisonDate != null) {
			return true;
		} else if (originalDate != null && comparisonDate != null) {
			return originalDate.compareTo(comparisonDate) < 0;
		} else {
			return false;
		}
	}
	
	public static boolean isDateBefore(Date originalDate, Date comparisonDate) {
		if (originalDate != null && comparisonDate != null) {
			return originalDate.compareTo(comparisonDate) > 0;
		} else if (originalDate == null && comparisonDate != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isDateSame(Date originalDate, Date comparisonDate) {
		if (originalDate != null && comparisonDate != null) {
			return originalDate.compareTo(comparisonDate) == 0;
		} else if (originalDate == null && comparisonDate == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isDateOnOrAfter(Date originalDate, Date comparisonDate) {
		if (originalDate == null && comparisonDate != null) {
			return true;
		} else if (originalDate != null && comparisonDate != null) {
			return originalDate.compareTo(comparisonDate) <= 0;
		} else {
			return false;
		}
	}
	
	public static boolean isDateOnOrBefore(Date originalDate, Date comparisonDate) {
		if (originalDate != null && comparisonDate != null) {
			return originalDate.compareTo(comparisonDate) >= 0;
		} else if (originalDate == null && comparisonDate != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isDateBetweenDates(Date rangeStartDate, Date rangeEndDate, Date comparisonDate) {
		return isDateOnOrAfter(rangeStartDate, comparisonDate) && isDateOnOrBefore(rangeEndDate, comparisonDate);
	}

	public static Date getFirstDayOfYear(String xmlDate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertXmlStringToDate(xmlDate));
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		
		return cal.getTime();
	}

	public static Date getLastDayOfYear(String xmlDate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertXmlStringToDate(xmlDate));
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DATE, 31);
		
		return cal.getTime();
	}

}
