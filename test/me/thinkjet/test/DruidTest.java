package me.thinkjet.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.jfinal.plugin.activerecord.DbKit;

public class DruidTest {

	@Test
	public void test() {
		int i = 0;
		while (i < 1000) {
			Connection c;
			try {
				c = DbKit.getConnection();
				Thread t = new ConCase(c);
				t.run();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public class ConCase extends Thread {
		Connection c;

		public ConCase(Connection c) {
			this.c = c;
		}

		@Override
		public void run() {
			try {
				c.wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
