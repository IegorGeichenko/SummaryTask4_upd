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
 * User's unpaid booking request on home page
 * @author Iegor
 *
 */
public class UsersBookingRequestsQueueServlet extends HttpServlet {

	private static final long serialVersionUID = 1359020957917580962L;
	private static final Logger LOG = Logger.getLogger(UsersBookingRequestsQueueServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		User user = (User) request.getSession().getAttribute("user");
		List<BookingRequest> bookingRequestsQueue = dbm.selectUserBookingRequestsQueue(user.getId());
		request.setAttribute("bookingRequestsQueue", bookingRequestsQueue);
		request.getRequestDispatcher(Path.PAGE_USER_REQUESTS_QUEUE).forward(request, response);
		LOG.trace("Request for user's booking requests in queue for payment processed");
	}
}