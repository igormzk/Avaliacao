package br.com.avaliacao.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que abre e fecha a comunicação com o banco de dados
 * @author Igor Muzeka
 * @version 1.0
 */
public class ConnectionFactory {
	
	private static Connection con;
	
	public static Connection open() {
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/avaliacao", "postgres", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
