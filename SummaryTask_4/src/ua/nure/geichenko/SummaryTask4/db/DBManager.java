package ua.nure.geichenko.SummaryTask4.db;



import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequest;
import ua.nure.geichenko.SummaryTask4.model.entity.bookingrequest.BookingRequestState;
import ua.nure.geichenko.SummaryTask4.model.entity.order.Order;
import ua.nure.geichenko.SummaryTask4.model.entity.room.Room;
import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomBusyState;
import ua.nure.geichenko.SummaryTask4.model.entity.room.RoomCategory;
import ua.nure.geichenko.SummaryTask4.model.entity.user.User;
import ua.nure.geichenko.SummaryTask4.util.DateUtils;

/**
 * DB manager. Works with MySQL DB.
 * 
 * @author Iegor
 * 
 */

public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class); 
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	private static DBManager instance;

	private DBManager() {
	}

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			// ST4DB - the name of data source
			DataSource ds = (DataSource)envContext.lookup("jdbc/ST4DB");
			con = ds.getConnection();
		} catch (NamingException ex) {
			LOG.error("Cannot obtain a connection from the pool", ex);			
		}
		return con;
	}


	/*****************************************
	 * User entity access methods
	 * 
	 * ***************************************
	 */
	/**
	 * Insert User in data base
	 * 
	 * @param user
	 *            User that will be inserted
	 * 
	 */
	public void insertUser(User user) {
		final String SQL_INSERT_USER = "INSERT INTO users (login,password,first_name,last_name,role_id) VALUES(?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQL_INSERT_USER);
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setInt(k++, user.getRoleId());
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a user", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Returns User by login
	 * 
	 * @param login
	 *            User login
	 */
	public User selectUserByLogin(String login) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("SELECT * FROM users WHERE login=?");
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			System.out.println(ex);
			LOG.error("Cannot obtain a user by its login", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return user;
	}

	/**
	 * Returns User by id
	 * 
	 * @param id
	 *            User id
	 */
	public User selectUserById(long id) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM users WHERE id=?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			System.out.println(ex);
			LOG.error("Cannot obtain a user by its id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return user;
	}
	
	/**
	 * Returns All users 
	 * 
	 */
	public List<User> selectAllUsers() {
		List<User> usersList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM users WHERE users.role_id = 3");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				usersList.add(extractUser(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain room items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return usersList;
	}

	/**
	 * Updates user
	 * 
	 * @param user
	 * 
	 */
	public void updateUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE users SET password=?, first_name=?, last_name=?" + "WHERE id=?");
			int k = 1;
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setInt(k, user.getId());
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a user", ex);
		} finally {
			close(con);
			close(pstmt);
		}
	}

	/**
	 * Delete user by id
	 * 
	 * @param id
	 */
	public void deleteUser(long id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM users WHERE id=?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a user by its id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Return list of all users
	 * 
	 */
	public List<User> findAllUsers() {
		List<User> usersList = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				usersList.add(extractUser(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain menu items", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return usersList;
	}

	/*****************************************
	 * Booking request entity access methods
	 * 
	 * ***************************************
	 */
	/**
	 * Insert booking request in db
	 */
	public void insertBookingRequest(BookingRequest br) {
		final String SQL_INSERT_BOOKING_REQUEST = "INSERT INTO booking_request (rooms_amount, date_arrival,date_check_out,user_id,category_id,date_booking, request_state,room_id) VALUES(?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQL_INSERT_BOOKING_REQUEST);
			int k = 1;
			pstmt.setInt(k++, br.getRoomsAmount());
			pstmt.setDate(k++, br.getDateArrival());
			pstmt.setDate(k++, br.getDateCheckOut());
			pstmt.setInt(k++, br.getUserId());
			pstmt.setInt(k++, br.getCategory().getValue());
			pstmt.setDate(k++, br.getBookingDate());
			pstmt.setInt(k++, br.getRequestStateValue().getValue());
			pstmt.setInt(k++, br.getRoomId());
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot insert a booking request ", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Returns Booking request by id
	 * 
	 * @param id
	 *            booking request id
	 */
	public BookingRequest selectBookingRequestById(int id) {
		BookingRequest br = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("SELECT * FROM booking_request WHERE id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				br = extractBookingRequest(rs);
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a booking request by id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return br;
	}

	/**
	 * Return full list of booking requests
	 * 
	 */
	public List<BookingRequest> selectAllbookingRequests() {
		List<BookingRequest> brList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT * FROM booking_request INNER JOIN users ON users.id = booking_request.user_id WHERE request_state="
							+ BookingRequestState.IN_PROGRESS.getValue());
			while (rs.next()) {
				brList.add(extractBookingRequest(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain booking request items", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return brList;
	}
	
	
	

	/**
	 * Returns list of booking requests in state - BOOKED.
	 */
	public List<BookingRequest> selectAllBookedBookingRequests() {
		List<BookingRequest> brList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM booking_request WHERE request_state="
					+ BookingRequestState.BOOKED.getValue());
			while (rs.next()) {
				brList.add(extractBookingRequest(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain booking request items", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return brList;
	}
	
	/**
	 * Returns list of booking requests in state - BOOKED and PAID.
	 */
	public List<BookingRequest> selectAllBookedPaidBookingRequests() {
		List<BookingRequest> brList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM booking_request WHERE request_state="
					+ BookingRequestState.BOOKED.getValue() + " or request_state="+ BookingRequestState.PAID.getValue());
			while (rs.next()) {
				brList.add(extractBookingRequest(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain booking request items", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return brList;
	}

	/**
	 * Return list of booking request for current user
	 * 
	 * @param userId
	 *            user's id
	 */
	public List<BookingRequest> selectUserBookingRequests(int userId) {
		List<BookingRequest> bookingRequests = new ArrayList<BookingRequest>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM booking_request INNER JOIN users ON users.id = booking_request.user_id WHERE user_id=? and booking_request.request_state=2");
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookingRequests.add(extractBookingRequest(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain booking request items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return bookingRequests;
	}
	
	/**
	 * Return list of booking requests for certain room
	 * 
	 * @param roomId
	 *            id of room
	 */
	public List<BookingRequest> selectBookingRequestsByRoomId(int roomId) {
		List<BookingRequest> bookingRequests = new ArrayList<BookingRequest>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM booking_request WHERE room_id=?");
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookingRequests.add(extractBookingRequest(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain booking request items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return bookingRequests;
	}

	/**
	 * Return list of user's booking requests in state BOOKED
	 * 
	 * @param userId
	 *            user's id
	 * 
	 */
	public List<BookingRequest> selectUserBookingRequestsQueue(int userId) {
		List<BookingRequest> bookingRequests = new ArrayList<BookingRequest>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM booking_request INNER JOIN users ON users.id = booking_request.user_id WHERE user_id=? and booking_request.request_state=3");
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookingRequests.add(extractBookingRequest(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain booking request items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return bookingRequests;
	}

	/**
	 * Updates booking request by setting room id
	 * 
	 * @param roomId
	 *            room id
	 */
	public void updateBookingRequestRoomId(int bookingRequestId, int roomId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE booking_request SET room_id=?, request_state=2 WHERE id=?");
			int k = 1;
			pstmt.setInt(k++, roomId);
			pstmt.setInt(k++, bookingRequestId);
			pstmt.executeUpdate();
			commit(con);

		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a user", ex);
		} finally {
			close(con);
			close(pstmt);

		}
	}

	/**
	 * Updates booking request by setting date of booking
	 * 
	 * @param bookingRequestId
	 *            booking requestId id
	 */
	public void updateBookingRequestBookingDate(int bookingRequestId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE booking_request SET date_booking=? WHERE id=?");
			pstmt.setDate(1, DateUtils.getDateFromString(sdf.format(new Date(System.currentTimeMillis())), DATE_FORMAT));
			pstmt.setInt(2, bookingRequestId);
			pstmt.executeUpdate();
			commit(con);
			LOG.trace("Booking request: " + bookingRequestId + ", booking date updated");
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a booking request", ex);
		} finally {
			close(con);
			close(pstmt);

		}
	}

	/**
	 * Updates booking request by setting certain request state
	 * 
	 * @param bookingRequestId
	 *            booking requestId id
	 * @param requestState
	 *            state of booking request
	 */
	public void updateBookingRequestRequestState(int requestState, int bookingRequestId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE booking_request SET request_state=? WHERE id=?");
			int k = 1;
			pstmt.setInt(k++, requestState);
			pstmt.setInt(k++, bookingRequestId);
			pstmt.executeUpdate();
			commit(con);
			LOG.trace("Booking request, with id= " + bookingRequestId + " turned in requestState=" + requestState);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a booking request", ex);
		} finally {
			close(con);
			close(pstmt);
		}
	}

	/**
	 * Updates booking request by setting certain request state
	 * 
	 * @param bookingRequestId
	 *            booking requestId id
	 * @param bill
	 *            booking request's bill
	 */
	public void updateBookingRequestBill(int bill, int bookingRequestId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE booking_request SET bill=? WHERE id=?");
			int k = 1;
			pstmt.setInt(k++, bill);
			pstmt.setInt(k++, bookingRequestId);
			pstmt.executeUpdate();
			commit(con);
			LOG.trace("Booking request, with id= " + bookingRequestId + ", bill: " + bill);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a booking request", ex);
		} finally {
			close(con);
			close(pstmt);
		}
	}

	/**
	 * Delete booking request
	 * 
	 * @param bookingRequest
	 *            booking request which will be deleted
	 */
	public void deleteBookingRequest(BookingRequest bookingRequest) {
		Connection con = null;
		PreparedStatement pstmtUpdate = null;
		PreparedStatement pstmtDelete = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);

			pstmtUpdate = con.prepareStatement("UPDATE rooms SET busy_state=" + RoomBusyState.FREE.getStateIdValue()
					+ " WHERE id=?");
			pstmtUpdate.setInt(1, bookingRequest.getRoomId());
			pstmtUpdate.executeUpdate();

			pstmtDelete = con.prepareStatement("DELETE FROM booking_request WHERE id=?");
			pstmtDelete.setInt(1, bookingRequest.getId());
			pstmtDelete.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot delete booking request and change room busy state", ex);
		} finally {
			close(rs);
			close(pstmtUpdate);
			close(pstmtDelete);
			close(con);
		}
	}

	/*********************************************
	 * Room access methods *******************************************
	 */
	/**
	 * Returns list of free rooms
	 */
	public List<Room> selectAllFreeRooms() {
		List<Room> roomsList = new ArrayList<Room>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM rooms INNER JOIN categories ON categories.id = rooms.category_id WHERE rooms.busy_state = 0");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomsList.add(extractRoom(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain room items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return roomsList;
	}

	/**
	 * Returns sorted list of free rooms
	 * 
	 * @param sortCriteriaValue
	 *            value of sort criteria
	 */
	public List<Room> sortedSelectAllFreeRooms(int sortCriteriaValue) {
		List<Room> roomsList = new ArrayList<Room>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			String sortField = null;
			switch (sortCriteriaValue) {
			case 1:
				 sortField = "";
				break;
			case 2:
				 sortField = "price";
				break;
			case 3:
				 sortField = "place_amount";
				break;
			case 4:
				 sortField = "category_id";
				break;
			case 5:
				 sortField = "number";
				break;
			default:
				 sortField = "";
				break;
			}
			pstmt = con.prepareStatement("SELECT * FROM rooms INNER JOIN categories ON categories.id = rooms.category_id WHERE rooms.busy_state = 0 ORDER BY " + sortField);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomsList.add(extractRoom(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain room items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return roomsList;
	}

	/**
	 * Returns list of free rooms which satisfy to user's request
	 * 
	 * @param category
	 *            room category
	 * @param roomsAmount
	 * 
	 */
	public List<Room> selectRoomByUserRequest(int category, int roomsAmount) {
		List<Room> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM rooms INNER JOIN categories ON categories.id = rooms.category_id WHERE category_id=? and place_amount=? and rooms.busy_state = 0");
			pstmt.setInt(1, category);
			pstmt.setInt(2, roomsAmount);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(extractRoom(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain room items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}

	/**
	 * Returns room by id
	 * 
	 * @param id
	 *            room id
	 */
	public Room selectRoomById(int id) {
		Room room = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("SELECT * FROM rooms WHERE id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				room = extractRoom(rs);
			}
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a room by id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return room;
	}

	/**
	 * Set room busy state OCCUPIED by room id
	 * 
	 * @param roomId
	 *            room id
	 */
	public void updateRoomBusyStateOccupiedById(int roomId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE rooms SET busy_state=" + RoomBusyState.OCCUPIED.getStateIdValue()
					+ " WHERE id=?");
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
			commit(con);
			LOG.trace("Room, with id= " + roomId + " turned in busy_state=" + RoomBusyState.OCCUPIED.getStateIdValue());
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a user by its id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Set room busy state RESERVED by room id
	 * 
	 * @param roomId
	 *            room id
	 */
	public void updateRoomBusyStateBookedById(int roomId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE rooms SET busy_state=" + RoomBusyState.RESERVED.getStateIdValue()
					+ " WHERE id=?");
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
			commit(con);
			LOG.trace("Room, with id= " + roomId + " turned in busy_state= " + RoomBusyState.RESERVED.getStateIdValue());
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a user by its id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Set room busy state FREE by room id
	 * 
	 * @param roomId
	 *            room id
	 */
	public void updateRoomBusyStateFreeById(int roomId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE rooms SET busy_state=" + RoomBusyState.FREE.getStateIdValue()
					+ " WHERE id=?");
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
			commit(con);
			LOG.trace("Room, with id= " + roomId + " turned in busy_state= " + RoomBusyState.FREE.getStateIdValue());
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain a user by its id", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Returns list of free rooms by category
	 * 
	 * @param category
	 *            category of room
	 */
	public List<Room> findAllRoomsByCategory(RoomCategory category) {
		List<Room> roomsList = new ArrayList<Room>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM rooms WHERE category_id = 0");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomsList.add(extractRoom(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain room items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return roomsList;
	}

											/*********************************************
											 * Order access methods *******************************************
											 */

	/**
	 * Insert order in data base
	 */
	public void insertOrder(Order order) {
		final String SQL_INSERT_ORDER = "INSERT INTO orders (room_id, user_id, date_arrival,date_check_out,bill,date_booking) VALUES(?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQL_INSERT_ORDER);
			int k = 1;
			pstmt.setInt(k++, order.getRoomId());
			pstmt.setInt(k++, order.getUserId());
			pstmt.setDate(k++, order.getDateArrival());
			pstmt.setDate(k++, order.getDateCheckOut());
			pstmt.setLong(k++, order.getBill());
			pstmt.setDate(k++, order.getDateBooking());
			pstmt.executeUpdate();
			commit(con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a user", ex);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	/**
	 * Returns list of user's orders
	 * 
	 * @param userId
	 *            User id
	 */
	public List<Order> selectUserOrders(int userId) {
		List<Order> userOrders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM orders INNER JOIN users ON users.id = orders.user_id WHERE user_id=?");
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userOrders.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain order items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return userOrders;
	}
	
	
	/**
	 * Returns list of orders by room id
	 * 
	 * @param roomId
	 *            id of room
	 */
	public List<Order> selectOrdersByRoomId(int roomId) {
		List<Order> roomOrders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("SELECT * FROM orders WHERE room_id=?");
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomOrders.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain order items", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return roomOrders;
	}
	

	
	public List<Order> selectAllOrders() {
		List<Order> orders = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM orders;");
			while (rs.next()) {
				orders.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain order items", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return orders;
	}

	/**
	 * Extracts a user entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return User entity
	 */
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}

	/**
	 * Extracts a room entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return Room entity
	 */
	private Room extractRoom(ResultSet rs) throws SQLException {
		Room room = new Room();
		room.setId(rs.getInt("id"));
		room.setNumber(rs.getInt("number"));
		room.setPlaceAmount(rs.getInt("place_amount"));
		room.setFloor(rs.getInt("floor"));
		room.setCategoryId(rs.getInt("category_id"));
		room.setPrice(rs.getInt("price"));
		room.setBusyStateId(rs.getInt("busy_state"));
		return room;
	}

	/**
	 * Extracts an booking request entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a booking request entity will be
	 *            extracted.
	 * @return
	 */
	private BookingRequest extractBookingRequest(ResultSet rs) throws SQLException {
		BookingRequest br = new BookingRequest();
		br.setId(rs.getInt("id"));
		br.setRoomsAmount(rs.getInt("rooms_amount"));
		br.setRoomId(rs.getInt("room_id"));
		br.setUserId(rs.getInt("user_id"));
		br.setCategory(rs.getInt("category_id"));
		br.setBill(rs.getInt("bill"));
		br.setDateArrival(rs.getDate("date_arrival"));
		br.setDateCheckOut(rs.getDate("date_check_out"));
		br.setBookingDate(rs.getDate("date_booking"));
		return br;
	}

	private Order extractOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setRoomId(rs.getInt("room_id"));
		order.setUserId(rs.getInt("user_id"));
		order.setDateArrival(rs.getDate("date_arrival"));
		order.setDateCheckOut(rs.getDate("date_check_out"));
		order.setDateBooking(rs.getDate("date_booking"));
		order.setBill(rs.getInt("bill"));
		return order;
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error("Cannot commit transaction and close connection", ex);
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a result set", ex);
			}
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a statement", ex);
			}
		}
	}

	/**
	 * Commit the given connection.
	 * 
	 * @param con
	 *            Connection to be commited.
	 */
	private void commit(Connection con) {
		if (con != null) {
			try {
				con.commit();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	/**
	 * Rollbacks the given connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}
}