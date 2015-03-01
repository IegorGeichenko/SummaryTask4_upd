package ua.nure.geichenko.SummaryTask4.model.entity.order;

import java.io.Serializable;
import java.sql.Date;

import ua.nure.geichenko.SummaryTask4.db.DBManager;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;

/**
 * Describes the order entity
 * 
 * @author Iegor
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = -14773346161997564L;

	private int userId;
	private int roomId;
	private long bill;
	private Date dateArrival;
	private Date dateCheckOut;
	private Date dateBooking;
	private Room room;

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

	public Room getRoom() {
		return DBManager.getInstance().selectRoomById(roomId);
	}

	public Date getDateCheckOut() {
		return dateCheckOut;
	}

	public void setDateCheckOut(Date dateCheckOut) {
		this.dateCheckOut = dateCheckOut;
	}

	public long getBill() {
		return bill;
	}

	public void setBill(long bill) {
		this.bill = bill;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getDateBooking() {
		return dateBooking;
	}

	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", roomId=" + roomId + ", bill=" + bill + ", dateArrival=" + dateArrival
				+ ", dateCheckOut=" + dateCheckOut + ", dateBooking=" + dateBooking + ", room=" + room + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bill ^ (bill >>> 32));
		result = prime * result + ((dateArrival == null) ? 0 : dateArrival.hashCode());
		result = prime * result + ((dateBooking == null) ? 0 : dateBooking.hashCode());
		result = prime * result + ((dateCheckOut == null) ? 0 : dateCheckOut.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + roomId;
		result = prime * result + userId;
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
		Order other = (Order) obj;
		if (bill != other.bill) {
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
		if (room == null) {
			if (other.room != null) {
				return false;
			}
		} else if (!room.equals(other.room)) {
			return false;
		}
		if (roomId != other.roomId) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}
}
