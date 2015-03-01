package ua.nure.geichenko.SummaryTask4.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.user.Role;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * Servlet provides registration of user
 * @author Iegor
 *
 */
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = -2893165737031548145L;

	private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.PAGE_REGISTRATION).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager db = DBManager.getInstance();
		
		String firstName = request.getParameter("first_name");
		LOG.trace("Request parameter: firstName --> " + firstName);

		String lastName = request.getParameter("last_name");
		LOG.trace("Request parameter: lastName --> " + lastName);

		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		String passwordConf = request.getParameter("password_conf");
		
		User checkUserUnique = db.selectUserByLogin(login);

		if (checkUserUnique != null) {
			LOG.trace("Login already exist in system");
			request.getSession().setAttribute("error", "error.registration.login.exists");
			response.sendRedirect("/SummaryTask4/error");
		}  else if ( login.isEmpty() ||  password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || passwordConf.isEmpty()) {
			request.getSession().setAttribute("error", "error.registration.empty.fields");
			LOG.trace("Empty fields found when registrate");
			response.sendRedirect("/SummaryTask4/error");
		} else if (!password.equals(passwordConf)) {
			request.getSession().setAttribute("error", "error.registration.passw.unconf");
			LOG.trace("Password unconfirmed");
			response.sendRedirect("/SummaryTask4/error");
		} else {
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setLogin(login);
			user.setPassword(password);
			user.setRoleId(Role.CLIENT.getRoleId());
			db.insertUser(user);
			request.getSession().setAttribute("greeting", "Welcome, " + firstName);
			response.sendRedirect("/SummaryTask4/home");
		}	
	}
}
