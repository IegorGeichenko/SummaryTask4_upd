package ua.nure.geichenko.SummaryTask4.model.entity.room;

/**
 * 
 * Describes the hotel room state
 * entity
 * @author Iegor
 *
 */
 
public enum RoomBusyState {
	FREE(0), RESERVED(1), OCCUPIED(2);

	private int stateIdValue;

	private RoomBusyState(int stateIdValue) {
		this.stateIdValue = stateIdValue;
	}

	public int getStateIdValue() {
		return stateIdValue;
	}
}