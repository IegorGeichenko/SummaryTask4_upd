package ua.nure.geichenko.SummaryTask4.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.util.Path;
import ua.nure.geichenko.SummaryTask4.web.controller.user.UsersBookingRequestsQueueServlet;

public class ErrorServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ErrorServlet.class);
	private static final long serialVersionUID = -6612397323806356939L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
		LOG.trace("An error occurred ");
	}
}
