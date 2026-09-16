package com.commonfloor.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DatabaseTest {

    private Connection connection;

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/selenium_automation";
    private static final String USER    = "root";
    private static final String PASS    = "root";

    @BeforeTest
    public void connectToDatabase() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected to database: selenium_automation");
    }

    @Test(description = "Verify database connection is established")
    public void testDatabaseConnection() {
        Assert.assertNotNull(connection, "Database connection should not be null");
        System.out.println("testDatabaseConnection PASSED");
    }

    @Test(description = "Select all books from book_detl table")
    public void testSelectBooks() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM selenium_automation.book_detl";
        ResultSet rs = stmt.executeQuery(query);

        int rowCount = 0;
        System.out.println("\nBooks in selenium_automation.book_detl:");
        System.out.println("--------------------------------------------------");
        while (rs.next()) {
            String author    = rs.getString("author");
            String title     = rs.getString("title");
            String publisher = rs.getString("publisher");
            int    price     = rs.getInt("price");
            System.out.println("Author: " + author + " | Title: " + title
                + " | Publisher: " + publisher + " | Price: " + price);
            rowCount++;
        }
        System.out.println("--------------------------------------------------");
        System.out.println("Total rows: " + rowCount);

        Assert.assertTrue(rowCount > 0, "book_detl table should have at least 1 record");
        System.out.println("testSelectBooks PASSED — " + rowCount + " rows found");
    }

    @AfterTest
    public void closeConnection() throws Exception {
        if (connection != null) {
            connection.close();
            System.out.println("Database connection closed");
        }
    }
}
