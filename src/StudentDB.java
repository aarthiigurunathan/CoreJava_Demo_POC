import java.sql.*;

public class StudentDB extends Exception {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1";
    static final String USER = "test1";
    static final String PASS = "Test1234";

    public static void main(String[] args) {

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {

            String drop1 = "DROP TABLE IF EXISTS candidateinfo";
            stmt.executeUpdate(drop1);

            //Creating Table
            String sql = "CREATE TABLE IF NOT EXISTS CandidateInfo " + "(studentID INTEGER , "
                    + " FirstName VARCHAR(255), " + " LastName VARCHAR(255)," +  " JoiningDate date, " + " DOB date, "
                    + " MobileNo bigint," + " EmailID nVARCHAR(255),"
                    + " DepartmentID  INTEGER , " + " Department VARCHAR(255), " + " PRIMARY KEY ( studentID ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            Statement st = conn.createStatement();

//            join table
            ResultSet res = st.executeQuery("select s.studentID, s.FirstName, s.LastName, s.JoiningDate, s.DOB, s.MobileNo, s.EmailID, s.DepartmentID, d.Department\n" +
                    "from studentinfo s\n" +
                    "join Department d\n" +
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
                String Department = res.getString("Department");

//                PreparedStatement p = null;
//                String s1 = "update CandidateInfo";
//                p = conn.prepareStatement(s1);
//                p.execute();

                // Beautification of output
                System.out.format(
                        "%10s%10s%10s%15s%15s%20s%20s%20s%20s\n", studentID, FirstName, LastName, JoiningDate, DOB, MobileNo, EmailID, Department, DepartmentID );
            }

            // Step 7: Close the connection
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

