package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * provide the confirmation
 * of payment
 * 
 */

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.util.DateUtils;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides confirmation of payment of booking request
 * @author Iegor
 */
public class ConfirmPaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 4581786204477109071L;
	private static final Logger LOG = Logger.getLogger(ConfirmPaymentServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		HttpSession session = request.getSession();
		BookingRequest bookingRequest = dbm.selectBookingRequestById(Integer.parseInt(request.getParameter("bookingRequestId")));
		int fullDaysofBooking = (int)(DateUtils.fullDaysBetweenDates(bookingRequest.getDateArrival(), bookingRequest.getDateCheckOut()));
		Room room = dbm.selectRoomById(Integer.parseInt(request.getParameter("roomId")));
		session.setAttribute("room", room);
		session.setAttribute("bookingRequest", bookingRequest);
		session.setAttribute("fullDaysofBooking", fullDaysofBooking);
		request.getRequestDispatcher(Path.PAGE_PAYMENT).forward(request, response);
		LOG.trace("Booking request with id: "+bookingRequest.getId()+" - request for payment "); 
	}
}