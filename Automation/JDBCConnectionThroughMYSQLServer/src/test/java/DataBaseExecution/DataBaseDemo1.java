package DataBaseExecution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DataBaseDemo1 {
	private Connection connection;
	@Test
	public void establishDataConnection() throws Exception{
		String databaseURL="jdbc:mysql://localhost:3306/selenium_automation";
		String user="root";
		String password="root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("connection to Database");
		Connection con =DriverManager.getConnection(databaseURL,user,password);
		Statement stmt=con.createStatement();
		
		String query=" select * from selenium_automation.book_detl";
		ResultSet rs= stmt.executeQuery(query);
		
		while (rs.next()) {
			String auth=rs.getString("author");
			String tit =rs.getString("title");
			System.out.println("author:" + auth + "title:"+tit);
			
		}
		
		
}
}

