package ua.nure.geichenko.SummaryTask4.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Contains methods and constants
 * for dates manipulations
 * @author Iegor
 *
 */
public class DateUtils {

	public static final int MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
/**
 * 
 * @param strDate
 * 				string value of date
 * @param dateFormat 
 * 				date format
 * @return date from given string
 */
	public static Date getDateFromString(String strDate, String dateFormat) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = new Date(sdf.parse(strDate).getTime());
		} catch (ParseException e) {
			/*NOP*/
		}
		return date;
	}
		/**
		 * Returns quantity of days between two dates
		 * @param dateFrom
		 * 				start date
		 * @param dateTo
		 * 				final date
		 */
	public static long fullDaysBetweenDates(Date dateFrom, Date dateTo) {
		return (dateTo.getTime() - dateFrom.getTime()) / MILLISECONDS_IN_DAY;
	}
}