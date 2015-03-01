package ua.nure.geichenko.SummaryTask4.model.entity.room;

import java.io.Serializable;

/**
 * Describes the hotel room
 * entity
 * @author Iegor
 *
 */
public class Room implements Serializable {

	private static final long serialVersionUID = -5147341111598587109L;

	private int id;
	private int number;
	private int placeAmount;
	private int floor;
	private int price;
	private int categoryId;
	private int busyStateId;
	private String categoryName;

	public int getNumber() {
		return number; 
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPlaceAmount() {
		return placeAmount;
	}

	public void setPlaceAmount(int placeAmount) {
		this.placeAmount = placeAmount;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getBusyStateId() {
		return busyStateId;
	}

	public void setBusyStateId(int busyState) {
		this.busyStateId = busyState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return RoomCategory.valueOf(categoryId).getName();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", placeAmount=" + placeAmount + ", floor=" + floor
				+ ", price=" + price + ", categoryName=" + categoryName + ", busyStateId=" + busyStateId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + busyStateId;
		result = prime * result + categoryId;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + floor;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + placeAmount;
		result = prime * result + price;
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
		Room other = (Room) obj;
		if (busyStateId != other.busyStateId) {
			return false;
		}
		if (categoryId != other.categoryId) {
			return false;
		}
		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (floor != other.floor) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (placeAmount != other.placeAmount) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		return true;
	}
}
