import java.sql.*;

public class Department {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1";
    static final String USER = "test1";
    static final String PASS = "Test1234";

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String dropq = "DROP TABLE IF EXISTS department";
            stmt.executeUpdate(dropq);

//            create table
            String sql = "CREATE TABLE IF NOT EXISTS DEPARTMENT " +
                    "(DepartmentID INTEGER , " +
                    " Department VARCHAR(255), " +
                    " PRIMARY KEY ( DepartmentID ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

//            Insert value
            System.out.println("Inserting records into the table...");
            sql = "INSERT INTO Department VALUES (101, 'CSE')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES (102, 'EEE')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES (103, 'IT')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(104, 'ECE')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(105, 'Arch')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(106, 'MECH')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Department VALUES(000, 'Choose a ID')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
