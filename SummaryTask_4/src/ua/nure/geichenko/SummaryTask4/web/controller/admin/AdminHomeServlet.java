package ua.nure.geichenko.SummaryTask4.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.util.Path;

/**
 * 
 * @author Iegor
 *
 */
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = -5709343920513790603L;
	private static final Logger LOG = Logger.getLogger(AdminHomeServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 LOG.trace("Current role of user: admin");
		request.getRequestDispatcher(Path.PAGE_HOME_ADMIN).forward(request, response);

	}
}
