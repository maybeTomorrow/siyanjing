package me.thinkjet.auth;

import com.jfinal.plugin.IPlugin;

/**
 * 
 * @ClassName AuthPlugin
 * @author johnny_zyc
 * @Modified 2013-4-13 下午1:22:18
 * 
 */
public class AuthPlugin implements IPlugin {

	public AuthPlugin() {

	}

	public boolean start() {
		AuthManager.init();
		return true;
	}

	public boolean stop() {
		return true;
	}

}
