package ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest;

import java.io.Serializable;
import java.sql.Date;

import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomCategory;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequestState;

/**
 * Describes the booking request
 * entity
 * @author Iegor
 *
 */
public class BookingRequest implements Serializable {

	private static final long serialVersionUID = 4674969354906808505L;

	private int id;
	private int userId;
	private int roomsAmount;
	private Date dateArrival;
	private Date dateCheckOut;
	private RoomCategory category;
	private Date dateBooking;
	private BookingRequestState requestState;
	private int roomId;
	private int bill;

	public int getRoomsAmount() {
		return roomsAmount;
	}

	public void setRoomsAmount(int roomId) {
		this.roomsAmount = roomId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}

	public Date getDateCheckOut() {
		return dateCheckOut;
	}

	public void setDateCheckOut(Date dateCheckOut) {
		this.dateCheckOut = dateCheckOut; 
	}

	public RoomCategory getCategory() {
		return category;
	}

	public void setCategory(int categoryId) {
		this.category = RoomCategory.valueOf(categoryId);
	}

	public Date getBookingDate() {
		return dateBooking;
	}

	public void setBookingDate(Date bookingDate) {
		this.dateBooking = bookingDate;
	}

	public BookingRequestState getRequestStateValue() {
		return requestState;
	}

	public void setRequestStateValue(int requestState) {
		this.requestState = BookingRequestState.valueOf(requestState); 
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "BookingRequest [roomsAmount=" + roomsAmount + ", dateArrival=" + dateArrival
				+ ", dateCheckOut=" + dateCheckOut + ", category=" + category + ", dateBooking=" + dateBooking
				+ ", requestState=" + requestState + ", roomId=" + roomId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateArrival == null) ? 0 : dateArrival.hashCode());
		result = prime * result + ((dateBooking == null) ? 0 : dateBooking.hashCode());
		result = prime * result + ((dateCheckOut == null) ? 0 : dateCheckOut.hashCode());
		result = prime * result + id;
		result = prime * result + ((requestState == null) ? 0 : requestState.hashCode());
		result = prime * result + roomId;
		result = prime * result + roomsAmount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BookingRequest other = (BookingRequest) obj;

		if (category != other.category) {
			return false;
		}
		if (dateArrival == null) {
			if (other.dateArrival != null) {
				return false;
			}
		} else if (!dateArrival.equals(other.dateArrival)) {
			return false;
		}
		if (dateBooking == null) {
			if (other.dateBooking != null) {
				return false;
			 }
		} else if (!dateBooking.equals(other.dateBooking)) {
			return false;
		}
		if (dateCheckOut == null) {
			if (other.dateCheckOut != null) { 
				return false;
				} 
		} else if (!dateCheckOut.equals(other.dateCheckOut)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (requestState != other.requestState) {
			return false;
		}
		if (roomId != other.roomId) {
			return false;
		}
		if (roomsAmount != other.roomsAmount) {
			return false;
		}
		return true;
	}
}
