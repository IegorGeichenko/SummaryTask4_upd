package ua.nure.geichenko.SummaryTask4.web.controller.manager;

import java.io.IOException;
import java.sql.Date;
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
import ua.nure.geichenko.SummaryTask4.util.DateUtils;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Provides room searching by user request using parameters: room category,
 * rooms amount
 * 
 * @author Iegor
 *
 */
public class RoomFinderServlet extends HttpServlet {

	private static final long serialVersionUID = -1217164557095669410L;
	private static final Logger LOG = Logger.getLogger(RoomFinderServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomsAmount = request.getParameter("roomsAmount");
		String category = request.getParameter("categoryId");
		String bookingRequestId = request.getParameter("bookingRequestId");
		Date checkIn = DateUtils.getDateFromString(request.getParameter("dateCheckIn"), "yyyy-mm-dd");
		Date checkOut = DateUtils.getDateFromString(request.getParameter("dateCheckOut"), "yyyy-mm-dd");
		DBManager dbm = DBManager.getInstance();
		List<BookingRequest> bookingRequestsByRoom = new ArrayList<BookingRequest>();
		List<Order> ordersByRoom = new ArrayList<Order>();
		List<Room> result = new ArrayList<Room>();

		List<Room> suitableListRooms = dbm.selectRoomByUserRequest(Integer.parseInt(category),
				Integer.parseInt(roomsAmount));
		for (Room room : suitableListRooms) {
			bookingRequestsByRoom = dbm.selectBookingRequestsByRoomId(room.getId());
			ordersByRoom = dbm.selectOrdersByRoomId(room.getId());
			if (bookingRequestsByRoom.size() > 0) {
				for (BookingRequest br : bookingRequestsByRoom) {
					if (!(checkIn.before(br.getDateArrival()) && checkOut.after(br.getDateCheckOut()))
							&& !(checkIn.after(br.getDateArrival()) && checkOut.before(br.getDateCheckOut()))
							&& !(checkIn.before(br.getDateArrival()) && checkOut.after(br.getDateArrival()))
							&& !(checkIn.after(br.getDateArrival()) && checkIn.before(br.getDateCheckOut()))
							&& !(checkIn.equals(br.getDateArrival()) || checkOut.equals(br.getDateCheckOut()))) {
						result.add(room);
					}
				}
			} else if (ordersByRoom.size() > 0) {
				for (Order order : ordersByRoom) {
					if (!(checkIn.before(order.getDateArrival()) && checkOut.after(order.getDateCheckOut()))
							&& !(checkIn.after(order.getDateArrival()) && checkOut.before(order.getDateCheckOut()))
							&& !(checkIn.before(order.getDateArrival()) && checkOut.after(order.getDateArrival()))
							&& !(checkIn.after(order.getDateArrival()) && checkIn.before(order.getDateCheckOut()))
							&& !(checkIn.equals(order.getDateArrival()) || checkOut.equals(order.getDateCheckOut()))) {
						result.add(room);
					}
				}
			} else {
				result.add(room);
			}
		}
		request.setAttribute("suitableListRooms", result);
		request.setAttribute("bookingRequestId", bookingRequestId);
		request.getRequestDispatcher(Path.PAGE_ROOM_FINDER).forward(request, response);
		LOG.trace("Request for hotel room search processed.");

	}
}
