package PocNew;

import java.io.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DepartmentCount {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmtp = null;
        // Open a connection
        try {
            // 1. Load the properties file
            Properties props = new Properties();
            props.load(new FileInputStream("d:/POC-Data/demo.properties"));

            // 2. Read the props
            String theUser = props.getProperty("user");
            String thePassword = props.getProperty("password");
            String theDburl = props.getProperty("dburl");

            System.out.println("Connecting to database...");
            System.out.println("Database URL: " + theDburl);
            System.out.println("User: " + theUser);

            // 3. Get a connection to database
            conn = DriverManager.getConnection(theDburl, theUser, thePassword);

            System.out.println("\nConnection successful!\n");
            stmtp = conn.createStatement();

            //Query to get the number of rows in a table
            String query = "select count(*) from StudentData";
            //Executing the query
            ResultSet rs = stmtp.executeQuery(query);
            //Retrieving the result
            rs.next();
            int count = rs.getInt(4);
            System.out.println("CSE: " + count);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
