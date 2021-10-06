package PocNew;

import java.io.*;
import java.sql.*;
import java.util.*;

public class StudentData extends Exception {

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

//            Creating Table
            String drop = "DROP TABLE IF EXISTS StudentData";
            stmt.executeUpdate(drop);

            String sql = "CREATE TABLE IF NOT EXISTS StudentData"
                    + "(StudentID int NOT NULL AUTO_INCREMENT, " + " FirstName VARCHAR(255), "
                    + " LastName VARCHAR(255), " + " DepartmentID VARCHAR(255), " + " JoiningDate VARCHAR(255), "
                    + " DOB VARCHAR(255), " + " MobileNo bigint," + " EmailID nVARCHAR(255),"
                    + " PRIMARY KEY ( StudentID ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

//            Inserting values
            System.out.println("Inserting records into the table...");

            sql = "INSERT INTO StudentData VALUES (01, 'Aarthii', 'Gurunathan', 'IT' , '15-12-2020' , '07-10-2020' , 1234567989 , 'aarth@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO StudentData VALUES (02, 'Prasanna', 'Venkat', 'ECE' , '05-1-2020' , '08-10-2020' , 1234567989 , 'prasve@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO StudentData VALUES(03, 'Samvidha', 'Prasad', 'ARCH' , '15-10-2020' , '09-10-2020' , 1234567989 , 'sams@gmail.com')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

//            join table
            Statement st1 = conn.createStatement();
            ResultSet res = st1.executeQuery("select s.studentID, s.FirstName, s.LastName, s.JoiningDate, s.DOB, s.MobileNo, s.EmailID, s.DepartmentID, d.POC1.Department\n" +
                    "from StudentData s\n" +
                    "join DepartmentDetails d\n" +
                    "On s.DepartmentID = d.DepartmentID");

            while (res.next()) {
                String studentID = res.getString("studentID");
                String FirstName = res.getString("FirstName");
                String LastName = res.getString("LastName");
                String JoiningDate = res.getString("JoiningDate");
                String DOB = res.getString("DOB");
                String MobileNo = res.getString("MobileNo");
                String EmailID = res.getString("EmailID");
                String DepartmentID = res.getString("DepartmentID");
                String Department = res.getString("POC1.Department");

                System.out.format(
                        "%10s%10s%10s%15s%15s%20s%20s%20s%20s\n", studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, Department, DepartmentID);
            }

//           file read and inser

            FileInputStream fstream = new FileInputStream("D:\\POC-Data\\input.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            ArrayList list = new ArrayList();
            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }
            Iterator itr;
            for (itr = list.iterator(); itr.hasNext(); ) {
                String str = itr.next().toString();
                String[] splitSt = str.split("\t");
                String sid = "", fname = "", lname = "", deptID = "", joinDate = "", dob = "", phNo = "", email = "";

                for (int i = 0; i <= splitSt.length; i++) {
                    sid = splitSt[0];
                    fname = splitSt[1];
                    lname = splitSt[2];
                    deptID = splitSt[3];
                    joinDate = splitSt[4];
                    dob = splitSt[5];
                    phNo = splitSt[6];
                    email = splitSt[7];
                }
                ResultSet rsSet = null;
                Statement Stmnt = conn.createStatement();

                rsSet = Stmnt.executeQuery("select * from DepartmentDetails");
                while (rsSet.next()) {
                    if (deptID.equals(rsSet.getString("DepartmentID"))) {
                        deptID = new String(rsSet.getString("POC1.Department"));
                    }
                }
                ResultSet resSet = Stmnt.executeQuery("select StudentID from StudentData");

                while (resSet.equals(sid)){
                    Stmnt.executeUpdate("update StudentData set StudentID=" + sid + "FirstName=" + fname + "LastName=" + lname + "DepartmentID=" + deptID + "JoiningDate=" + joinDate + "DOB=" + dob + "MobileNo=" + phNo + "EmailID=" + email);
                }

                Stmnt.executeUpdate("insert into StudentData(StudentID, FirstName, LastName, DepartmentID, JoiningDate, DOB, MobileNo, EmailID) values('" + sid + "','" + fname + "','" + lname + "','" + deptID + "','" + joinDate + "','" + dob + "','" + phNo + "','" + email + "')");
                System.out.println("inserted " + fname + "into db");

            }

//            //File Write
            try ( PrintWriter fwrite = new PrintWriter(new OutputStreamWriter
                    (new BufferedOutputStream(new FileOutputStream("D:\\POC-Data\\Output_student_Details.txt")), "UTF-8"));) {
                try(ResultSet res2 = stmt.executeQuery("select * from StudentData")) {
                    while (res2.next()) {
                        fwrite.append(new Integer(res2.getInt("StudentID")).toString()).append("\t") //1
                                .append(res2.getString("FirstName")).append("\t")  //2
                                .append(res2.getString("LastName")).append("\t")      //3
                                .append(res2.getString("JoiningDate")).append("\t") //4
                                .append(res2.getString("DOB")).append("\t")    //5
                                .append(new Long(res2.getLong("MobileNo")).toString()).append("\t")   //6
                                .append(res2.getString("EmailID")).append("\t")      //7
                                .append(res2.getString("DepartmentID")).append("\t")   //8
                                .println();
                        System.out.println("File Has Been Written in txt");

                        String studentID = res2.getString("studentID");
                        String FirstName = res2.getString("FirstName");
                        String LastName = res2.getString("LastName");
                        String JoiningDate = res2.getString("JoiningDate");
                        String DOB = res2.getString("DOB");
                        String MobileNo = res2.getString("MobileNo");
                        String EmailID = res2.getString("EmailID");
                        String DepartmentID = res2.getString("DepartmentID");

                        System.out.format(
                                "%10s%10s%10s%15s%15s%20s%20s%20s\n", studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, DepartmentID);

                    }
                    System.out.println("File Has Been Written ");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}








