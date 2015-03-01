package ua.nure.geichenko.SummaryTask4.room;

import org.junit.Test;

import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomCategory;

public class RoomCategoryTest {

	@Test
	public void testConstructor() {
		RoomCategory rc = RoomCategory.CAT_DELUXE;
		RoomCategory.valueOf(rc.toString());
	}
}
