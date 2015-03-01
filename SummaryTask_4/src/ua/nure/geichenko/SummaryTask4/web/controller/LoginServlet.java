package ua.nure.geichenko.SummaryTask4.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.user.Role;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides user's authorization in system
 * @author Iegor
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 6479534703178370343L;
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.PAGE_LOGIN).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login--> " + login);

		String password = request.getParameter("password");

		User user = db.selectUserByLogin(login);
		HttpSession session = request.getSession(true);

		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("user", user);
			if (Role.ADMIN.equals(Role.valueOf(user.getRoleId()))) {
				response.sendRedirect("/SummaryTask4/admin/home");
			} else if (Role.MANAGER.equals(Role.valueOf(user.getRoleId()))) {
				response.sendRedirect("/SummaryTask4/manager/home");
			} else {
				response.sendRedirect("/SummaryTask4/home");
			}
		} else {
			if (user == null || login.isEmpty() || password == null || password.isEmpty()) {
				LOG.trace("Error in input login-data");
				request.getSession().setAttribute("error", "errorlogin");
				response.sendRedirect("/SummaryTask4/error");
			}
		}
	}
}
