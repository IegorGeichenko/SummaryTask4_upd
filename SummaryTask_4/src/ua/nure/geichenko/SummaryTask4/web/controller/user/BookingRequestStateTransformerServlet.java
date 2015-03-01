package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 *  Provides transformation
 *  of booking request state
 *  to "BOOKED" and booking room busy state
 *  to "RESERVED". 
 * @author Iegor
 *
 */
public class BookingRequestStateTransformerServlet extends HttpServlet {

	private static final long serialVersionUID = 4034804056644128224L;
	private static final Logger LOG = Logger.getLogger(BookingRequestStateTransformerServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		int bookingRequestId = Integer.parseInt(request.getParameter("bookingRequestId"));
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		dbm.updateBookingRequestRequestState(3, bookingRequestId);
		dbm.updateBookingRequestBill((int)request.getSession().getAttribute("bookingRequestBill"), bookingRequestId);
		dbm.updateBookingRequestBookingDate(bookingRequestId);
		//dbm.updateRoomBusyStateBookedById(roomId);
		request.getRequestDispatcher(Path.PAGE_SUCCESS_BOOKING_REQUEST).forward(request, response);
		LOG.trace("Booking request with id: " + bookingRequestId + " updated by setting state: BOOKED; room id: " + roomId);
	}
}
