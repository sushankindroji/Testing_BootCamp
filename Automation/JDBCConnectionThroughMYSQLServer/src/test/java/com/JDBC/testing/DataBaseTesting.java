package com.JDBC.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import org.testng.annotations.Test;
public class DataBaseTesting {
	private Connection connection;
	@Test
	public void establishDataConnection() throws Exception{
		String databaseURL="jdbc:mysql://localhost:3306/selenium_automation";
		String user="root";
		String password="root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("connection to Database");
		
		connection=DriverManager.getConnection(databaseURL,user,password);
		if(connection==null) {
			System.out.println("connection to Database Failed");
		}
		else {
			System.out.println("connection to Database Sucessfully");
		}
		
	}
	

}
