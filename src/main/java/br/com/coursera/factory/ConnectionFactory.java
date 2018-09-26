/**
 * 
 */
package br.com.coursera.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jose
 *
 */
public abstract class ConnectionFactory {
	public static final String DRIVER = "org.postgresql.Driver";
	public static final String URL = "jdbc:postgresql://localhost/forum";
	public static final String USER = "alves";
	public static final String SENHA = "1234";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, SENHA);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
