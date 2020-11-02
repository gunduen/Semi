package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	public JDBCTemplate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
private static JDBCTemplate instance;
	
	public static JDBCTemplate getConnection() {
		if(instance==null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection createConnection() throws SQLException {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "gunduen_db";
		String password = "gunduenadmin";
		return DriverManager.getConnection(url, user, password);
	}

	public static void close(Connection conn) {
		try {
			if(!conn.isClosed() && conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(Statement stmtm) {
		try {
			if(!stmtm.isClosed() && stmtm!=null) {
				stmtm.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(!rset.isClosed() && rset!=null) {
				rset.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// DB 커밋, 롤백 메서드
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
