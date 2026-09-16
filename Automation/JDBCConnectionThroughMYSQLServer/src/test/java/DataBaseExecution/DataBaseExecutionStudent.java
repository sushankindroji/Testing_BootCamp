package DataBaseExecution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DataBaseExecutionStudent {

    @Test
    public void studentTable() throws Exception {
    	String databaseURL="jdbc:mysql://localhost:3306/selenium_automation";
		String user="root";
		String password="root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connection to Database");
        Connection con=DriverManager.getConnection(databaseURL,user,password);
        Statement stmt=con.createStatement();
        String query="select * from selenium_automation.stu_detl";
        ResultSet rs=stmt.executeQuery(query);
        
        while(rs.next()) {
            String stuCode=rs.getString("stu_code");
            String name=rs.getString("name");
            String deptCode=rs.getString("dept_code");
            String fine=rs.getString("fine");

            System.out.println(stuCode+" "+name+" "+deptCode+" "+fine);
        }
        con.close();
    }
}