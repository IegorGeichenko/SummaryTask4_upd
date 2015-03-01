package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomBusyState;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Provides the payment and making order from booking request, setting room busy
 * state OCCUPIED and booking request state PAID
 * @author Iegor
 */
public class SuccessPaymentServlet extends HttpServlet {

	private static final long serialVersionUID = -8102213429619843873L;
	private static final Logger LOG = Logger.getLogger(SuccessPaymentServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		BookingRequest bookingRequest = dbm.selectBookingRequestById(Integer.parseInt(request.getParameter("bookingRequestId")));
		Room room = dbm.selectRoomById(Integer.parseInt(request.getParameter("roomId")));
		User user = (User)request.getSession().getAttribute("user");
		Order order = new Order();
		order.setBill(bookingRequest.getBill());
		order.setDateArrival(bookingRequest.getDateArrival());
		order.setDateCheckOut(bookingRequest.getDateCheckOut());
		order.setRoomId(room.getId());
		order.setDateBooking(bookingRequest.getBookingDate());
		order.setUserId(user.getId());
		
		
		if (room.getBusyStateId() != RoomBusyState.OCCUPIED.getStateIdValue()) {
			//dbm.updateRoomBusyStateOccupiedById(room.getId());
			dbm.insertOrder(order);
			LOG.trace("Order created by user booking request confirmation");
			LOG.trace("Order info: " + order);
			dbm.updateBookingRequestRequestState(4, bookingRequest.getId());
		} else {
			request.getSession().setAttribute("error", "error.not.free.room");
			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
			LOG.trace("Requested room is not free now");
		}
		request.getRequestDispatcher(Path.PAGE_PAYMENT_PROCESSED).forward(request, response);
		LOG.trace("Order payment processed successful");
	}
}