package ua.nure.geichenko.SummaryTask4.model.entity.user;

/**
 * Contains roles of hotel users
 * @author Iegor
 *
 */
public enum Role {
	ADMIN(1), MANAGER(2), CLIENT(3);

	private int roleId;

	private Role(int roleId) {
		this.roleId = roleId;
	}

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public static Role valueOf(int value) {
		for (Role role : Role.values()) {
			if (role.getRoleId() == value) {
				return role;
			}
		}
		throw new IllegalArgumentException();
	}

	public int getRoleId() {
		return roleId;
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
