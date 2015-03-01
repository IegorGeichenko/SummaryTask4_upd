package ua.nure.geichenko.SummaryTask4.web.controller.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.util.Path;

public class ManagerHomeServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ManagerHomeServlet.class);
	private static final long serialVersionUID = 3936989028674182227L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.PAGE_HOME_MANAGER).forward(request, response);
		 LOG.trace("Current role of user: manager");
	}
}
