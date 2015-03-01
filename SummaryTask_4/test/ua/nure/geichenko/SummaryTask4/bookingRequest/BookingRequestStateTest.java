package ua.nure.geichenko.SummaryTask4.bookingRequest;

import org.junit.Test;

import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequestState;

public class BookingRequestStateTest {

	@Test
	public void testConstructor() {
		BookingRequestState bs = BookingRequestState.BOOKED;
		BookingRequestState.valueOf(bs.toString());
	}
}
