package ua.nure.geichenko.SummaryTask4.model.job;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomBusyState;
import ua.nure.geichenko.SummaryTask4.util.DateUtils;

/**
 * Provides the room busy state changing control
 * by analyzing order and booking request data
 * @author Iegor
 *
 */
 
public class RoomBusyStateControllerJob implements Runnable {

	private static final Logger LOG = Logger.getLogger(RoomBusyStateControllerJob.class);

	@Override
	public void run() {
		DBManager dbm = DBManager.getInstance();
		Date currentDate = new Date(System.currentTimeMillis());
		while (true) {
			List<Order> orders = dbm.selectAllOrders();
			List<BookingRequest> bookingrequests = dbm.selectAllBookedPaidBookingRequests();
			List<Room> rooms = dbm.selectAllFreeRooms();
			if (orders.size() > 0) {
				for (Room room : rooms) {
					for (Order order : orders) {
						if (room.getId() == order.getRoomId()
								&& DateUtils.fullDaysBetweenDates(order.getDateArrival(), currentDate) == 0
								&& room.getBusyStateId() != RoomBusyState.OCCUPIED.getStateIdValue()) {
							dbm.updateRoomBusyStateOccupiedById(order.getRoomId());
							LOG.trace("Room: id= " + order.getRoomId() + ", in busy state: OCCUPIED");
						} else if (DateUtils.fullDaysBetweenDates(order.getDateCheckOut(), currentDate) == 0) {
							dbm.updateRoomBusyStateFreeById(order.getRoomId());
							LOG.trace("Room: id= " + order.getRoomId() + ", in busy state: FREE (from order)");
						}
					}

					if (bookingrequests.size() > 0) {
						for (BookingRequest br : bookingrequests) {
							if (room.getId() == br.getRoomId()
									&& DateUtils.fullDaysBetweenDates(br.getDateArrival(), currentDate) == 0
									&& room.getBusyStateId() != RoomBusyState.OCCUPIED.getStateIdValue()) {
								dbm.updateRoomBusyStateOccupiedById(br.getRoomId());
								LOG.trace("Room: id= " + br.getRoomId() + ", in busy state: OCCUPIED");
							} else if (DateUtils.fullDaysBetweenDates(br.getDateCheckOut(), currentDate) == 0) {
								dbm.updateRoomBusyStateFreeById(br.getRoomId());
								LOG.trace("Room: id= " + br.getRoomId() + ", in busy state: FREE (from booking request)");
							}
						}
					}
					try {
						Thread.sleep(300000);
					} catch (InterruptedException e) { /* NOP */
					}
				}
			}
		}
	}
}
