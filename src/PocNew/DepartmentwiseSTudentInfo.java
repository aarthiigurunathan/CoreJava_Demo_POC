package PocNew;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DepartmentwiseSTudentInfo extends Exception {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmtp = null;
        Object var3 = null;

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

            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                    new BufferedOutputStream(new FileOutputStream("D:\\POC-Data\\Output_depertmentwiseStudentReport.txt")), "UTF-8"));
                 Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {
                try (ResultSet res = statement.executeQuery("SELECT  studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, DepartmentID FROM studentData where DepartmentId = 'ECE'")) {
                    while (res.next()) {
                        String studentID = res.getString("studentID");
                        String FirstName = res.getString("FirstName");
                        String LastName = res.getString("LastName");
                        String JoiningDate = res.getString("JoiningDate");
                        String DOB = res.getString("DOB");
                        String MobileNo = res.getString("MobileNo");
                        String EmailID = res.getString("EmailID");
                        String DepartmentID = res.getString("DepartmentID");

                        writer.append(studentID).append("\t").append(FirstName).append("\t")
                                .append(LastName).append("\t").append(DepartmentID).append("\t")
                                .append(JoiningDate).append("\t").append(DOB).append("\t")
                                .append(MobileNo).append("\t").append(EmailID).append("\t").println();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
