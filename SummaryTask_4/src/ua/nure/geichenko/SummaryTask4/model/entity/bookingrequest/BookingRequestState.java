package ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest;

/**
 * Describes the booking request state
 * entity
 * @author Iegor
 *
 */
public enum BookingRequestState {
	
	IN_PROGRESS(1, "in progress"), PROCESSED(2, "processed"), BOOKED(3, "booked"), PAID(4, "paid");

	private final String name;
	private final int value;

	private BookingRequestState(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static BookingRequestState valueOf(int value) {
		for (BookingRequestState requestState : BookingRequestState.values()) {
			if (requestState.getValue() == value) {
				return requestState;
			}
		}
		throw new IllegalArgumentException();
	}
}
