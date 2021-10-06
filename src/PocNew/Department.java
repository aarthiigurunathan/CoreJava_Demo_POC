package PocNew;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Department {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
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
            stmt = conn.createStatement();

            String dropq = "DROP TABLE IF EXISTS DepartmentDetails";
            stmt.executeUpdate(dropq);

//            create table
            String sql = "CREATE TABLE IF NOT EXISTS DepartmentDetails " +
                    "(DepartmentID VARCHAR(255) , " +
                    " Department VARCHAR(255), " +
                    " PRIMARY KEY ( DepartmentID ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

//            Insert value
            System.out.println("Inserting records into the table...");
            sql = "INSERT INTO Department VALUES (1, 'CSE')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES (2, 'EEE')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES (3, 'IT')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(4, 'ECE')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(5, 'Arch')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(6, 'MECH')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

            //file write
            try ( PrintWriter fwrite = new PrintWriter(new OutputStreamWriter
                    (new BufferedOutputStream(new FileOutputStream("D:\\POC-Data\\List_Of_Department.txt")), "UTF-8"));) {
                try(ResultSet res2 = stmt.executeQuery("select * from DepartmentDetails")) {
                    while (res2.next()) {
                        fwrite.append(res2.getString("DepartmentID")).append("\t")
                                .append(res2.getString("Department")).append("\t")
                                .println();
                        System.out.println("File Has Been Written in txt");

                        String DepartmentID = res2.getString("DepartmentID");
                        String Department = res2.getString("Department");

                        System.out.format("%10s%10s\n", DepartmentID, Department);
                    }

                    System.out.println("File Has Been Written");

                }
            }


        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
