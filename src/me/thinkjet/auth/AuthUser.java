package me.thinkjet.auth;

import java.io.Serializable;

import me.thinkjet.model.Users;

/**
 * 
 * @ClassName AuthUser
 * @author johnny_zyc
 * @Modified 2013-4-13 下午1:22:48
 * 
 */
public class AuthUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6381769478484169816L;
	private Users user;
	private String[] roles;

	public AuthUser(final Users user, final String[] roles) {
		this.user = user;
		this.roles = roles;
	}

	public Users getUser() {
		return user;
	}

	public String[] getRoles() {
		return roles;
	}

}
