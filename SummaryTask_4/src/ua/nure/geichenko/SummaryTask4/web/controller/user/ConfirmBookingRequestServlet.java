package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.util.DateUtils;
import ua.nure.geichenko.SummaryTask4.util.Path;


/**
 * Servlet provides confirmation of booking request
 * @author Iegor
 *
 */
public class ConfirmBookingRequestServlet extends HttpServlet{
	
	private static final long serialVersionUID = -2537301809125521502L;
	private static final Logger LOG = Logger.getLogger(ConfirmBookingRequestServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		Room room = dbm.selectRoomById(Integer.parseInt(request.getParameter("roomId")));
		BookingRequest bookingRequest = dbm.selectBookingRequestById(Integer.parseInt(request.getParameter("bookingRequestId")));
		int bookingRequestBill = (int)(DateUtils.fullDaysBetweenDates(bookingRequest.getDateArrival(), bookingRequest.getDateCheckOut()) * room.getPrice());
		long days = DateUtils.fullDaysBetweenDates(bookingRequest.getDateArrival(), bookingRequest.getDateCheckOut());
		request.setAttribute("bookingRequest", bookingRequest);
		request.setAttribute("room", room);
		request.setAttribute("days", days);
		request.getSession().setAttribute("bookingRequestBill", bookingRequestBill);
		request.getRequestDispatcher(Path.PAGE_CONFIRM_BOOKING_REQUEST).forward(request, response);
		LOG.trace("Booking request with id: "+bookingRequest.getId()+" confirmation created"); 
	}
}

