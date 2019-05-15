package com.c72;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

enum DBinsertStatus{
	NOTHING_CHANGED,SOMETHING_CHANGED,EXCEPTION_THROWN
}

public class DBManager {
	private final String DriverPath;
	private final String ConnectPath;
	private final String UserName;
	private final String Password;
	
	private Connection conn;
	public static class Builder {
		private String DriverPath="com.mysql.cj.jdbc.Driver";
		private String ConnectPath="jdbc:mysql://localhost:3306/learn_mysql?serverTimezone=GMT";
		
		private final String UserName;
		private final String Password;
		
		public static enum Singlton {
			INSTANCE;
			private DBManager manager;
			private Singlton() {
			}
			public DBManager getInstance(String DriverPath,String ConnectPath,String UserName,String Password) {
				manager=new DBManager(DriverPath,ConnectPath,UserName,Password);
				return manager;
			}
		}
		
		public Builder(String UserName,String Password) {
			this.UserName=UserName;
			this.Password=Password;
		}
		public Builder Driver(String val) {
			DriverPath=val;
			return this;
		}
		public Builder Connect(String val) {
			ConnectPath=val;
			return this;
		}
		public DBManager getInstance() {
			return Singlton.INSTANCE.getInstance(DriverPath, ConnectPath, UserName, Password);
		}
		public DBManager build() {
			return getInstance();
		}
	}
	private DBManager(String DriverPath,String ConnectPath,String UserName,String Password) {
		// TODO Auto-generated constructor stub
		this.DriverPath=DriverPath;
		this.ConnectPath=ConnectPath;
		this.UserName=UserName;
		this.Password=Password;
	}
	public void initManager() {
		try {
			if(conn==null) {
				Class.forName(DriverPath);
				conn=DriverManager.getConnection(ConnectPath,UserName,Password);
			}else {
					resetManager();
			}
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void resetManager() {
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
			conn=DriverManager.getConnection(ConnectPath,UserName,Password);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String select(String sqlString) {
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder tempresult=new StringBuilder();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sqlString);
			if(rs==null) {
				throw new Exception("Nothing return back,Please check your sql string");
			}else {
				int col=rs.getMetaData().getColumnCount();
				while(rs.next()) {
					for(int i=1;i<=col;i++) {
						tempresult.append(rs.getString(i)+"\t");
					}
					tempresult.append("\n");
				}
				return tempresult.toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "exception";
		}finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	public DBinsertStatus update(String sqlString) {
		Statement stmt=null;
		int count;
		try {
			stmt=conn.createStatement();
			count=stmt.executeUpdate(sqlString);
			if(count==0) {
				return DBinsertStatus.NOTHING_CHANGED;
			}else {
				return DBinsertStatus.SOMETHING_CHANGED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return DBinsertStatus.EXCEPTION_THROWN;
		}finally {
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) {
		        	sqlEx.printStackTrace();
		        } // ignore

		        stmt = null;
		    }
		}
	}
	public void disConnect() {
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn=null;
	}
}
