package org.chris.study.concurrency.jcip.examples.chap03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Listing 3.10. Using ThreadLocal to ensure thread confinement.
 */
public class ConnectionHolder {
	
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=FOOBAR";

	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
	
		@Override
		public Connection initialValue() {
			try {
				return DriverManager.getConnection(DB_URL);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	};
	
	public static Connection  getConnection()  {
		return  connectionHolder.get();
	}
}
