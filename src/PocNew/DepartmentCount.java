package PocNew;

import java.io.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DepartmentCount {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmtp = null;
        ResultSet rs1 = null;
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

            //count
            //Query to get the number of rows in a table
            rs1 = stmtp.executeQuery("SELECT studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, Department, DepartmentID FROM StudentData  Natural Join DepartmentDetails");
            int cse=0,it=0,eee=0,ece=0,arch=0,mech=0;
            System.out.println("while1");
            while (rs1.next()) {
                System.out.println("while2");
                String dept = rs1.getString("DepartmentID");
                if(dept.equals("CSE")){cse++;}
                else if(dept.equals("IT")){it++;}
                else if(dept.equals("EEE")){eee++;}
                else if(dept.equals("ECE")){ece++;}
                else if(dept.equals("Arch")){arch++;}
                else{mech++;}
                System.out.println("CSE : " + ece);
            }
            System.out.println("exit while");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
