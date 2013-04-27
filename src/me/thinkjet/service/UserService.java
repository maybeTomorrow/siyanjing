package me.thinkjet.service;

import java.util.Date;

import me.thinkjet.model.Users;
import me.thinkjet.utils.encoder.PasswordEncoder;

import org.apache.commons.lang3.StringUtils;

public class UserService {
	/**
	 * 
	 * @param loginName
	 *            username or password
	 * @return
	 */
	public Users getUser(String loginName) {
		return StringUtils.indexOf(loginName, '@') > 0 ? this
				.findUserByEmail(loginName) : this
				.findUserByUsername(loginName);
	}

	public String getEncodedPassword(String password, String username) {
		return PasswordEncoder.encode(password, username);
	}

	public void createUser(Users user) {
		user.set(
				"password",
				this.getEncodedPassword(user.getStr("password"),
						user.getStr("username"))).save();
	}

	public void modifyself(Users user) {
	}

	public void resetPassword(Users user) {
		user.set(
				"password",
				this.getEncodedPassword(user.getStr("password"),
						user.getStr("username"))).update();
	}

	public Users findUserByUsername(String username) {
		return Users.dao.findFirst("select * from users where username = ?",
				username);
	}

	public Users findUserByEmail(String email) {
		return Users.dao
				.findFirst("select * from users where email = ?", email);
	}

	public Users findUserByUId(Long uid) {
		return Users.dao.findById(uid);
	}

	public void UpdateUserStatus(Users u) {
		u.set("lastlogin", new Date()).update();
	}

	public boolean checkExist(String name) {
		return this.getUser(name) != null;
	}

}
