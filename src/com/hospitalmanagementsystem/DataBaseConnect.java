package com.hospitalmanagementsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseConnect {
	
	private static volatile Connection con;
	private DataBaseConnect() {
		try {
		InputStream is = new FileInputStream("src/information.properties");
		Properties prop = new Properties();
		prop.load(is);
		String url = prop.getProperty("url");
		String userName = prop.getProperty("user");
		String password = prop.getProperty("password");
		con = DriverManager.getConnection(url, userName, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private static class DataVaseConnectInner{
		private static volatile DataBaseConnect connect = new DataBaseConnect();
	}
	
	public static Connection getConnect()
	{
		final DataBaseConnect connect = DataVaseConnectInner.connect;
		return connect.con;
	}
		

}
