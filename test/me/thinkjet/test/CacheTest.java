package me.thinkjet.test;

import me.thinkjet.model.Users;

import org.junit.Test;

public class CacheTest {

	@Test
	public void test() {
		Users.dao.findByCache("test", "test", "test");
	}
}
