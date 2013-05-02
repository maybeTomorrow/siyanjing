package me.thinkjet.service;

import me.thinkjet.model.Sysconfig;

public class SysconfigService {

	public static String getValue(SysconfigKey key) {
		return Sysconfig.dao.findFirst(
				"select config_value from sysconfig where config_key=?",
				key.name()).getStr("config_value");
	}
}
