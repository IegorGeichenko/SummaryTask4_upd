package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomCategory;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequestState;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;

import ua.nure.geichenko.SummaryTask4.util.DateUtils;
import ua.nure.geichenko.SummaryTask4.util.Path;


/**
 * Servlet provides booking request creation 
 * by obtaining data from form
 * @author Iegor
 *
 */
public class BookingRequestServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(BookingRequestServlet.class);
	private static final long serialVersionUID = 2341699740348094752L;
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories", RoomCategory.values());
		request.getRequestDispatcher(Path.PAGE_BOOKING_REQUEST).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		BookingRequest bookingRequest = new BookingRequest();

		String checkInDate = request.getParameter("checkIn");

		LOG.trace("Request parameter: checkIn --> " + checkInDate);

		String checkOutDate = request.getParameter("checkOut");

		LOG.trace("Request parameter: checkOut --> " + checkOutDate);

		String roomsAmount = request.getParameter("roomsAmount");
		LOG.trace("Request parameter: roomsAmount --> " + roomsAmount);

		String roomCategory = request.getParameter("roomCategory");
		LOG.trace("Request parameter: roomCategory --> " + roomCategory);

		Date currentDate = DateUtils.getDateFromString(sdf.format(new Date(System.currentTimeMillis())), DATE_FORMAT);

		LOG.trace("Request parameter: current Date --> " + currentDate);

		Date dateCheckIn = DateUtils.getDateFromString(checkInDate, DATE_FORMAT);
		Date dateCheckOut = DateUtils.getDateFromString(checkOutDate, DATE_FORMAT);
		if (dateCheckIn == null || checkOutDate == null) {
			request.getSession().setAttribute("error", "error.reservation.empty.fields");
			response.sendRedirect("/SummaryTask4/error");
			LOG.trace("Incorrect input: Empty fields for date input found, filling required");
		} else if (dateCheckIn.after(dateCheckOut)) {
			request.getSession().setAttribute("error", "error.reservation.chInPrevChOut");
			response.sendRedirect("/SummaryTask4/error");
			LOG.trace("Incorrect input: Check-in must be previous check-out");
		} else if (dateCheckIn.equals(dateCheckOut)) {
			request.getSession().setAttribute("error", "error.reservation.arriv.eq.depart");
			response.sendRedirect("/SummaryTask4/error");
			LOG.trace("Incorrect input: Arrival and departure date can not be on the same day.");
		} else if (!currentDate.equals(dateCheckIn) && currentDate.after(dateCheckIn)) {
			request.getSession().setAttribute("error", "error.reservation.arriv.lesEq.curr");
			response.sendRedirect("/SummaryTask4/error");
			LOG.trace("Incorrect input: Arrival date shall not be earlier than today.");
		} else if ( roomsAmount == null || roomsAmount.isEmpty()) {
			request.getSession().setAttribute("error", "error.reservation.emptyRoomAmount");
			response.sendRedirect("/SummaryTask4/error");
			LOG.trace("Incorrect input: Empty field for rooms amount.");
		} else if (roomCategory == null || roomCategory.isEmpty()) {
			request.getSession().setAttribute("error", "error.reservation.emptyCategoryRoom");
			response.sendRedirect("/SummaryTask4/error");
			LOG.trace(" Incorrect input: Empty field for room category.");
		} else {
			bookingRequest.setCategory(Integer.parseInt(roomCategory));
			String dateFormat = "dd/MM/yyyy";
			bookingRequest.setDateArrival(DateUtils.getDateFromString(checkInDate, dateFormat));
			bookingRequest.setDateCheckOut(DateUtils.getDateFromString(checkOutDate, dateFormat));
			bookingRequest.setRoomsAmount(Integer.parseInt(roomsAmount));
			bookingRequest.setUserId(((User)request.getSession().getAttribute("user")).getId());
			bookingRequest.setBookingDate(new Date(System.currentTimeMillis()));
			bookingRequest.setRequestStateValue(BookingRequestState.IN_PROGRESS.getValue());
			bookingRequest.setRoomId(0);
			dbm.insertBookingRequest(bookingRequest);
			request.getRequestDispatcher(Path.PAGE_SUCCESS_RESERVATION).forward(request, response);
			LOG.trace("Booking request created successful");
		}

	}
}