package PocNew;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class StudentDelete extends Exception {

    static final String QUERY1 = "SELECT studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, DepartmentID FROM studentinfo";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        // Open a connection
        try {
            // 1. Load the properties file
            Properties props = new Properties();
            props.load(new FileInputStream("d:/demo.properties"));

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
            stmt = conn.prepareStatement("delete from StudentData where StudentID = ?");

            //           file read

            FileInputStream fstream = new FileInputStream("D:\\POC-Data\\deletestudent.txt");
            DataInputStream in = new DataInputStream(fstream);
            int count = in.available();
            byte[] input = new byte[count];
            in.read(input);
            for ( int b : input ) {
                System.out.print((char) b);
                stmt.setInt(1,b);
                stmt.executeUpdate();
            }


            //delete
//            System.out.println("Deleting row");
//            String sql3 = "DELETE FROM studentinfo WHERE StudentID = 10";
//            stmt.executeUpdate(sql3);
//
//            ResultSet rs1 = stmt.executeQuery(QUERY1);
//            while (rs1.next()) {
//                String studentID = rs1.getString("studentID");
//                String FirstName = rs1.getString("FirstName");
//                String LastName = rs1.getString("LastName");
//                String JoiningDate = rs1.getString("JoiningDate");
//                String DOB = rs1.getString("DOB");
//                String MobileNo = rs1.getString("MobileNo");
//                String EmailID = rs1.getString("EmailID");
//                String DepartmentID = rs1.getString("DepartmentID");
//
//                System.out.format(
//                        "%10s%10s%10s%15s%15s%20s%20s%20s\n", studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, DepartmentID);
//
//            }
        }
            catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
