package me.thinkjet.test;

import static org.junit.Assert.assertEquals;
import me.thinkjet.common.Config;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jfinal.ext.test.ControllerTestCase;

public class TestRoute extends ControllerTestCase {
	@BeforeClass
	public static void init() throws Exception {
		//start(new Config());
	}

	@Test
	public void testAController() throws Exception {
		invoke("/");
		assertEquals("test", findAttrAfterInvoke("name"));
	}

}