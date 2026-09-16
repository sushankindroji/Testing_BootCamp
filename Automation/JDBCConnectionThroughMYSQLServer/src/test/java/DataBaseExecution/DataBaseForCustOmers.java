package DataBaseExecution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DataBaseForCustOmers {

    @Test
    public void customerTable() throws Exception {
    	String databaseURL="jdbc:mysql://localhost:3306/selenium_automation";
		String user="root";
		String password="root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connection to Database");
        Connection con=DriverManager.getConnection(databaseURL,user,password);
        Statement stmt=con.createStatement();
        String query="select * from selenium_automation.cust_new";
        ResultSet rs=stmt.executeQuery(query);
        
        while(rs.next()) {
            String customerId=rs.getString("CUSTOMER_ID");
            String lastName=rs.getString("CUST_LAST_NAME");
            String dob=rs.getString("DATE_OF_BIRTH");
            String accountMgr=rs.getString("ACCOUNT_MGR_ID");

            System.out.println(customerId+" "+lastName+" "+dob+" "+accountMgr);
        }
        con.close();
    }
}