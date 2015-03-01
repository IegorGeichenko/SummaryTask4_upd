package ua.nure.geichenko.SummaryTask4.room;

import org.junit.Test;
import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomBusyState;


public class RoomBusyStateTest {

	@Test
	public void testConstructor() {
		RoomBusyState bs = RoomBusyState.FREE;
		RoomBusyState.valueOf(bs.toString());
	}
}
