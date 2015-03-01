package ua.nure.geichenko.SummaryTask4.dateUtils;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import ua.nure.geichenko.SummaryTask4.util.DateUtils;

public class DateUtilsTest {

	@Test
	public void testGetDateFromString() {
		String date = "22/11/2015";
		Date dateFromString = DateUtils.getDateFromString(date, "dd/MM/yyyy");
		assertEquals("2015-11-22", dateFromString.toString());
	}
	
	@Test
	public void testFullDaysBetweenDates() {
		String date1 = "22/11/2015";
		Date dateFromString1 = DateUtils.getDateFromString(date1, "dd/MM/yyyy");
		String date2 = "25/11/2015";
		Date dateFromString2 = DateUtils.getDateFromString(date2, "dd/MM/yyyy");
		assertEquals(3, DateUtils.fullDaysBetweenDates(dateFromString1, dateFromString2));
	}
}
