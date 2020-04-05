package it.polito.tdp.corsi.dp;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConncectDB {
	
	private static final String jdbcURL="jdbc:mariadb://localhost/iscritticorsi";
//	String jdbcURL="jdbc:mariadb://localhost/babs?user=root&password=Saraoiasa97"
	private static HikariDataSource ds;
	
	static Connection getConnection() {
		if(ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcURL);
			config.setUsername("root");
			config.setPassword("Saraoiasa97");
			
			config.addDataSourceProperty("cachePrepSims", true);
			config.addDataSourceProperty("prepSimtChacheSize", 250);
			config.addDataSourceProperty("prepStmCacheSqlLimit", "2048");
			
			ds= new HikariDataSource(config);
			
		}
		
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			System.out.println("Errore di connessione ad db");
			throw new RuntimeException(e);
		}
		
	}

}
