package br.com.greenpower.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDAO {
	
	public Connection conexao() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521/XEPDB1" , "JOAOLOCAL" , "231105");
	}
}