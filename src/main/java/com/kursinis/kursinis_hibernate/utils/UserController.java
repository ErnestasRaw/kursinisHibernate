package com.kursinis.kursinis_hibernate.utils;

import com.kursinis.kursinis_hibernate.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
	private static UserController single_instance = null;

	private User loggedInUser;

	private int loggedInUserId;

	public static UserController getInstance() {
		if ( single_instance == null ) {
			single_instance = new UserController();
		}

		return single_instance;
	}

	public void setLoggedInUser(User user) {
		loggedInUser = user;
		loggedInUserId = user.getId();
	}
}
