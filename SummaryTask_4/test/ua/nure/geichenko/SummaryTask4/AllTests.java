package ua.nure.geichenko.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.geichenko.SummaryTask4.bookingRequest.BookingRequestStateTest;
import ua.nure.geichenko.SummaryTask4.dateUtils.DateUtilsTest;
import ua.nure.geichenko.SummaryTask4.room.RoomBusyStateTest;
import ua.nure.geichenko.SummaryTask4.room.RoomCategoryTest;
import ua.nure.geichenko.SummaryTask4.user.UserRoleTest;

@RunWith(Suite.class)
@SuiteClasses({BookingRequestStateTest.class, RoomBusyStateTest.class,RoomCategoryTest.class,UserRoleTest.class, DateUtilsTest.class})
public class AllTests {}
