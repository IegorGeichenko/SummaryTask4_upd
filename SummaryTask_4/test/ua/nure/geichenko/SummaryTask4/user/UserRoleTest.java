package ua.nure.geichenko.SummaryTask4.user;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.geichenko.SummaryTask4.model.entity.user.Role;

public class UserRoleTest {

	@Test
	public void testConstructor() {
		Role ur = Role.ADMIN;
		Role.valueOf(ur.toString());
	}
}
