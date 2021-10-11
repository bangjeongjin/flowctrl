package flowctrl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

import javax.naming.NamingException;

public class DBConnection {
							
	public static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/oracleLocal");
			Connection conn = ds.getConnection();
			return conn;
		}
	

}