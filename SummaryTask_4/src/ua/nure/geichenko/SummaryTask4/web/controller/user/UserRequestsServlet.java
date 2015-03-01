package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * User's booking request from manager on home page
 * @author Iegor
 *
 */
public class UserRequestsServlet extends HttpServlet {

	private static final long serialVersionUID = 3334945083024933059L;
	private static final Logger LOG = Logger.getLogger(UserRequestsServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		User user = (User) request.getSession().getAttribute("user");
		List<BookingRequest> bookingRequests = dbm.selectUserBookingRequests(user.getId());
		request.setAttribute("bookingRequests", bookingRequests);
		request.getRequestDispatcher(Path.PAGE_USER_REQUESTS).forward(request, response);
		LOG.trace("Request for user's booking requests processed");
	}

}
