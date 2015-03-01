package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides generation of free rooms list
 * @author Iegor
 */
public class RoomsServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(RoomsServlet.class);

	private static final long serialVersionUID = -7021824678947309871L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		List<Room> freeRooms = dbm.selectAllFreeRooms();
		request.setAttribute("freeRooms", freeRooms);
		request.getRequestDispatcher(Path.PAGE_ROOMS).forward(request, response);
		LOG.trace("Request for free rooms in hotel processed");
	}
}
