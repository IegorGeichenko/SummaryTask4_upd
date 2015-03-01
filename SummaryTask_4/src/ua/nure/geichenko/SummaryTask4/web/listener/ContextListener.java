package ua.nure.geichenko.SummaryTask4.web.listener;

import javax.servlet.*;

/*import java.io.File;*/
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.model.job.BookingDateCheckerJob;
import ua.nure.geichenko.SummaryTask4.model.job.RoomBusyStateControllerJob;

/**
 * Context listener.
 * 
 * @author Iegor
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");
		// obtain file name with locales descriptions
		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		log("Servlet context initialization finished");
		String locales = servletContext.getInitParameter("locales");

		// save descriptions to servlet context
		servletContext.setAttribute("locales", locales);

		// thread for booking date control starts
		initThreadChecker();
		log("thread for booking date control starts");

		// thread for rooms busy state control starts
		initThreadRoomBusyStateController();
		log("thread for  rooms busy state control starts");

	}

	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		// do nothing
		log("Servlet context destruction finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
		} catch (Exception ex) {
			LOG.error("Cannot configure Log4j", ex);
		}
		log("Log4J initialization finished");
		log("Log4j has been initialized");
	}

	public void initThreadChecker() {
		Thread thread = new Thread(new BookingDateCheckerJob());
		thread.setDaemon(true);
		thread.start();

	}

	public void initThreadRoomBusyStateController() {
		Thread thread = new Thread(new RoomBusyStateControllerJob());
		thread.setDaemon(true);
		thread.start();

	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}