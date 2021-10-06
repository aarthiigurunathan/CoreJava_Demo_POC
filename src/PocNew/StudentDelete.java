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

            //           delete given id

            FileInputStream fstream = new FileInputStream("D:\\POC-Data\\deletestudent.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int count = in.available();
            String s;
            ArrayList list = new ArrayList();
            while ((s = br.readLine()) != null) {
                list.add(s);
                System.out.println(s);
                stmt.executeUpdate("delete from StudentData where studentID="+s);
            }

        }
            catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
