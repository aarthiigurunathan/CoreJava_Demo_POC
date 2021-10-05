import java.io.*;
import java.sql.*;
import java.util.*;

public class TableTest extends Exception {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1";
    static final String USER = "test1";
    static final String PASS = "Test1234";

    public static void main(String[] args) {

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();

        ) {
////            Creating Table
            String drop = "DROP TABLE IF EXISTS studentinfo";
            stmt.executeUpdate(drop);

            String sql = "CREATE TABLE IF NOT EXISTS StudentInfo "
                    + "(StudentID int, " + " FirstName VARCHAR(255), "
                    + " LastName VARCHAR(255), " + " DepartmentID int, " + " JoiningDate date, " + " DOB date, "
                    + " MobileNo bigint," + " EmailID nVARCHAR(255),"
                    + " PRIMARY KEY ( StudentID ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

//            Inserting values
            System.out.println("Inserting records into the table...");
            sql = "INSERT INTO StudentInfo VALUES(05, 'Keshav', 'Raghav', 101 , '2015-10-20' , '2005-10-20' , 1234567989 , 'keshrag@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO StudentInfo VALUES (03, 'Sidharth', 'Ram', 102 , '2005-11-20' , '2006-10-20' , 1234567989 , 'sidram@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO StudentInfo VALUES (01, 'Aarthii', 'Gurunathan', 103 , '2015-12-20' , '2007-10-20' , 1234567989 , 'aarth@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO StudentInfo VALUES (02, 'Prasanna', 'Venkat', 104 , '2005-1-20' , '2008-10-20' , 1234567989 , 'prasve@gmail.com')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO StudentInfo VALUES(04, 'Samvidha', 'Prasad', 105 , '2015-10-20' , '2009-10-20' , 1234567989 , 'sams@gmail.com')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

//           file insert
            Statement st = conn.createStatement();
            FileInputStream fstream = new FileInputStream("D:\\input.txt");
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

                for (int i = 0; i <= 8; i++) {
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

                rsSet = Stmnt.executeQuery("select * from department");
                while(rsSet.next()){
                    if(deptID.equals(rsSet.getString("Department"))){
                        deptID=new String(rsSet.getString("DepartmentID"));
                    }}int k = Stmnt.executeUpdate("insert into studentinfo(StudentID, FirstName, LastName, DepartmentID, JoiningDate, DOB, MobileNo, EmailID) values('" + sid + "','" + fname + "','" + lname + "','" + deptID + "','" + joinDate + "','"+dob +"','"+phNo+"','"+email+"')");
                System.out.println("inserted "+fname+"into db");

//                int k = st.executeUpdate("insert into studentinfo(StudentID, FirstName, LastName, DepartmentID, JoiningDate, DOB, MobileNo, EmailID) values ('" + sid + "','" + fname + "','" + lname + "','" + deptID + "','" + joinDate + "','" + dob + "','" + phNo + "','" + email + "')");
//
                // Step 7: Close the connection
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








///merge
//            Statement st = conn.createStatement();
//            ResultSet res = st.executeQuery("SELECT * FROM " + "StudentInfo" + " NATURAL JOIN " + "department");
//            System.out.println("ID" + " FirstName " + "LastName" + "Department" + "DepartmentID");
//            while (res.next()) {
//                String ID = res.getString("ID");
//                String FirstName = res.getString("FirstName");
//                String LastName = res.getString("LastName");
//                String Department = res.getString("Department");
//                String DepartmentID = res.getString("DepartmentID");
//
//                // Beautification of output
//                System.out.format(
//                        "%10s%10s%10s%10s%20s\n", ID, FirstName, LastName, Department, DepartmentID);
//            }
//            Statement st = conn.createStatement();
//            ResultSet res = st.executeQuery("select s.ID, s.FirstName, s.LastName, s.DepartmentID, d.Department\n" +
//                    "from studentinfo s\n" +
//                    "join Department d\n" +
//                    "On s.DepartmentID = d.DepartmentID");
//            System.out.println("ID" + " FirstName " + "LastName" + "Department" + "DepartmentID");
//            while (res.next()) {
//                String ID = res.getString("ID");
//                String FirstName = res.getString("FirstName");
//                String LastName = res.getString("LastName");
//                String Department = res.getString("Department");
//                String DepartmentID = res.getString("DepartmentID");
//
//                // Beautification of output
//                System.out.format(
//                        "%10s%10s%10s%10s%20s\n", ID, FirstName, LastName, Department, DepartmentID);
//            }