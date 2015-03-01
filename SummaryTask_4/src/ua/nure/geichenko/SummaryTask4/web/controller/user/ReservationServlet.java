package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.DateUtils;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides creation of order for room reservation
 * 
 * @author Iegor
 *
 */
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 2341699740348094752L;
	private static final Logger LOG = Logger.getLogger(ReservationServlet.class);
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("roomId", request.getParameter("room_id"));
		request.getRequestDispatcher(Path.PAGE_RESERVATION).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		int roomId = Integer.parseInt(request.getParameter("roomId"));

		Order order = new Order();
		Room room = dbm.selectRoomById(roomId);

		String checkInDate = request.getParameter("checkIn");

		LOG.trace("Request parameter: checkIn --> " + checkInDate);

		String checkOutDate = request.getParameter("checkOut");

		LOG.trace("Request parameter: checkOut --> " + checkOutDate);

		User user = (User) request.getSession().getAttribute("user");
		long fullDaysofBooking;

		Date currentDate = DateUtils.getDateFromString(sdf.format(new Date(System.currentTimeMillis())), DATE_FORMAT);
		Date dateCheckIn = DateUtils.getDateFromString(checkInDate, DATE_FORMAT);
		Date dateCheckOut = DateUtils.getDateFromString(checkOutDate, DATE_FORMAT);

		List<BookingRequest> bookingRequestsByRoom = dbm.selectBookingRequestsByRoomId(roomId);
		List<Order> ordersByRoom = dbm.selectOrdersByRoomId(roomId);

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
			LOG.trace("Arrival date shall not be earlier than today.");
		} else if (bookingRequestsByRoom.size() > 0) {
			for (BookingRequest br : bookingRequestsByRoom) {
				if ((dateCheckIn.before(br.getDateArrival()) && dateCheckOut.after(br.getDateCheckOut()))
						|| (dateCheckIn.after(br.getDateArrival()) && dateCheckOut.before(br.getDateCheckOut()))
						|| (dateCheckIn.before(br.getDateArrival()) && dateCheckOut.after(br.getDateArrival()))
						|| (dateCheckIn.after(br.getDateArrival()) && dateCheckIn.before(br.getDateCheckOut()))
						|| (dateCheckIn.equals(br.getDateArrival()) || dateCheckOut.equals(br.getDateCheckOut()))) {
					request.getSession().setAttribute("error", "error.reservation.dates");
					response.sendRedirect("/SummaryTask4/error");
					LOG.trace("Unfortunatelly, this room already booked on this dates.");
					return;
				}
			}
		}

		else if (ordersByRoom.size() > 0) {
			for (Order orderR : ordersByRoom) {
				LOG.trace("Inside orders");
				if ((dateCheckIn.before(orderR.getDateArrival()) && dateCheckOut.after(orderR.getDateCheckOut()))
						|| (dateCheckIn.after(orderR.getDateArrival()) && dateCheckOut.before(orderR.getDateCheckOut()))
						|| (dateCheckIn.before(orderR.getDateArrival()) && dateCheckOut.after(orderR.getDateArrival()))
						|| (dateCheckIn.after(orderR.getDateArrival()) && dateCheckIn.before(orderR.getDateCheckOut()))
						|| (dateCheckIn.equals(orderR.getDateArrival()) || dateCheckOut
								.equals(orderR.getDateCheckOut()))) {
					LOG.trace("Inside orders");
					request.getSession().setAttribute("error", "error.reservation.dates");
					response.sendRedirect("/SummaryTask4/error");
					LOG.trace("Unfortunatelly, this room already booked on this dates.");
					return;

				}
			}
		}
		order.setDateArrival(dateCheckIn);
		order.setDateCheckOut(dateCheckOut);
		order.setUserId(user.getId());
		fullDaysofBooking = DateUtils.fullDaysBetweenDates(dateCheckIn, dateCheckOut);
		order.setBill(fullDaysofBooking * room.getPrice());
		order.setRoomId(roomId);
		order.setDateBooking(new Date(System.currentTimeMillis()));
		LOG.trace("Request parameter: checkOut -->" + order.toString());
		request.getSession().setAttribute("roomId", roomId);
		request.getSession().setAttribute("fullDaysofBooking", fullDaysofBooking);
		request.getSession().setAttribute("order", order);
		response.sendRedirect("/SummaryTask4/confirmReservation");

	}
}
