package ua.nure.geichenko.SummaryTask4.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.util.Path;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = -4550032902039846116L;
	private static final Logger LOG = Logger.getLogger(HomeServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.PAGE_HOME).forward(request, response);
		 LOG.trace("Current role of user: client");
	}
}