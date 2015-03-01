package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides final level of room reservation
 * @author Iegor
 *
 */
public class SuccessReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 2974272910381537461L;

	private static final Logger LOG = Logger.getLogger(SuccessReservationServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		int roomId = (int) request.getSession().getAttribute("roomId");
		Order order = (Order) request.getSession().getAttribute("order");
		Room room = dbm.selectRoomById(roomId);
		if (room.getBusyStateId() != 1 && room.getBusyStateId() != 2) {
			//dbm.updateRoomBusyStateOccupiedById(roomId);
			dbm.insertOrder(order);
		} else {
			request.getSession().setAttribute("error", "error.not.free.room");
			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
			LOG.trace("Requested room is not free now");
		}
		request.getRequestDispatcher(Path.PAGE_SUCCESS_RESERVATION).forward(request, response);
		LOG.trace("Reservation processed successful");
	}
}