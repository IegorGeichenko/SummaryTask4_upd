package ua.nure.geichenko.SummaryTask4.model.entity.room;

/**
 * Describes the hotel room
 * category
 * @author Iegor
 *
 */
public enum RoomCategory {

	CAT_D(1, "CAT_D"), CAT_C(2, "CAT_C"), CAT_B(3, "CAT_B"), CAT_A(4, "CAT_A"), CAT_DELUXE(
			5, "CAT_DELUXE");

	private final int value;
	private final String name;

	private RoomCategory(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static RoomCategory valueOf(int value) {
		for (RoomCategory category : RoomCategory.values()) {
			if (category.getValue() == value) {
				return category;
			}
		}
		throw new IllegalArgumentException();
	}
}
