package ua.nure.geichenko.SummaryTask4.model.job;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.util.DateUtils;


/**
 * Provides the control of unpaid booking request
 * by comparing current date and date of booking
 * @author Iegor
 *
 */
public class BookingDateCheckerJob implements Runnable {
	
	private static final Logger LOG = Logger.getLogger(BookingDateCheckerJob.class);
	
	@Override
	public void run() {
		DBManager dbm = DBManager.getInstance();
		while (true) {
			List<BookingRequest> bookingRequestList = dbm.selectAllBookedBookingRequests();
			for (BookingRequest bookingRequest : bookingRequestList) {
				if (DateUtils.fullDaysBetweenDates(bookingRequest.getBookingDate(),
						new Date(System.currentTimeMillis())) > 2) { 
					dbm.deleteBookingRequest(bookingRequest);
					LOG.trace("Booking request with id= " +bookingRequest.getId()+" - deleted (expired of date)");
				}
			}
			try {
				Thread.sleep(DateUtils.MILLISECONDS_IN_DAY / 6);
			} catch (InterruptedException e) { /*NOP*/}
		}
	}
}
