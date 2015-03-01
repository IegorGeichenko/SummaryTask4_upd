package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides confirmation of order creation
 * @author Iegor
 */
public class ConfirmReservationServlet extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(ConfirmReservationServlet.class);
	private static final long serialVersionUID = -3094459748171189730L; 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	DBManager dbm = DBManager.getInstance();
		Room room = dbm.selectRoomById((int) request.getSession().getAttribute("roomId"));
		request.getSession().setAttribute("room", room);
		request.getRequestDispatcher(Path.PAGE_CONFIRM_RESERVATION).forward(request, response);
		LOG.trace("Confirmation of reservation room: " + room);
	}
}