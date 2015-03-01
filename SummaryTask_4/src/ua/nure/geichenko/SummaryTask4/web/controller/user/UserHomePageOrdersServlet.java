package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * User's orders from home page
 * @author Iegor
 *
 */
public class UserHomePageOrdersServlet extends HttpServlet {

	private static final long serialVersionUID = -9159934896957275668L;
	private static final Logger LOG = Logger.getLogger(UserHomePageOrdersServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		User user = (User) request.getSession().getAttribute("user");
		List<Order> userOrders = dbm.selectUserOrders(user.getId());
		request.getSession().setAttribute("userOrders", userOrders);
		request.getRequestDispatcher(Path.PAGE_USER_ORDERS).forward(request, response);
		LOG.trace("Request for user's orders processed");
	}
}