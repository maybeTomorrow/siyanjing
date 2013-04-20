package me.thinkjet.auth;

/**
 * 
 * @ClassName GlobeRolesIntercept
 * @author johnny_zyc
 * @Modified 2013-4-13 下午1:22:48
 * 
 */
public class GlobeRoles {
	private String pattern;
	private String[] access;

	public GlobeRoles(final String pattern, final String[] access) {
		this.pattern = pattern;
		this.access = access;
	}

	public String getPattern() {
		return pattern;
	}

	public String[] getAccess() {
		return access;
	}

	public boolean authorize(String role) {
		for (int i = 0; i < access.length; i++) {
			if (access[i].equals(role)) {
				return true;
			}
		}
		return false;
	}

}
