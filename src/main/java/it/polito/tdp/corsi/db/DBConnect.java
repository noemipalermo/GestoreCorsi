package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection() {
		
		String jdbcURL = "jdbc:mariadb://localhost/iscritticorsi?user=root&password=root";

		try {
			return DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			System.out.println("Errore connessione");
			e.printStackTrace();
			return null;
		}
	}
}
