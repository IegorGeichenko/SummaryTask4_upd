package ua.nure.geichenko.SummaryTask4.web.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.Path;

public class AllUsersServlet extends HttpServlet {

	private static final long serialVersionUID = -7503328899850007129L;
	private static final Logger LOG = Logger.getLogger(AllUsersServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = DBManager.getInstance();
		LOG.trace("done 1");
		List<Order> orders = dbm.selectAllOrders();
		List<User> users = dbm.selectAllUsers();
		Map<String, Integer> quantity = new HashMap<>();

		for (User usersS : users) {
			int temp = 0;
			for (Order ordersS : orders) {
				if (usersS.getId() == ordersS.getUserId()) {
					temp++;
				}
			}
			quantity.put(usersS.getLogin(), temp);
		}
		request.setAttribute("quantity", quantity);
		LOG.trace("done 2");
		request.getRequestDispatcher(Path.PAGE_All_USERS).forward(request, response);
		
	}

}
