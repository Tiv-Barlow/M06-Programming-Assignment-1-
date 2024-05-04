//M06 Programming Assignment (1)
//Ivy Tech Community College
//SDEV 200 - Java
//Professor Bumgardner
//Nativida Muhammad
// 04 May 2024

 import java.sql.*;

 public class Main {
     private Connection conn;

     public Main() {
         try {
             // Load the MySQL JDBC driver
             Class.forName("com.mysql.cj.jdbc.Driver");

             // Establish the database connection
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
             System.out.println("Connected to the database.");
         } catch (ClassNotFoundException e) {
             System.err.println("MySQL JDBC driver not found. Make sure it's included in your classpath.");
             e.printStackTrace();
         } catch (SQLException e) {
             System.err.println("Error connecting to the database.");
             e.printStackTrace();
         }
     }

     public void viewStaff(String id) {
         try {
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Staff WHERE id = ?");
             stmt.setString(1, id);
             ResultSet rs = stmt.executeQuery();

             // Process the result set, display staff information
             while (rs.next()) {
                 System.out.println("ID: " + rs.getString("id"));
                 System.out.println("Last Name: " + rs.getString("lastName"));
                 System.out.println("First Name: " + rs.getString("firstName"));
                 // Display other fields similarly
             }

             rs.close();
             stmt.close();
         } catch (SQLException e) {
             System.err.println("Error executing SQL query.");
             e.printStackTrace();
         }
     }

     public void insertStaff(String id, String lastName, String firstName, String mi, String address, String city,
                             String state, String telephone, String email) {
         try {
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
             stmt.setString(1, id);
             stmt.setString(2, lastName);
             stmt.setString(3, firstName);
             stmt.setString(4, mi);
             stmt.setString(5, address);
             stmt.setString(6, city);
             stmt.setString(7, state);
             stmt.setString(8, telephone);
             stmt.setString(9, email);

             int rowsAffected = stmt.executeUpdate();
             System.out.println(rowsAffected + " row(s) inserted.");

             stmt.close();
         } catch (SQLException e) {
             System.err.println("Error executing insertStaff query.");
             e.printStackTrace();
         }
     }

     public void updateStaff(String id, String lastName, String firstName, String mi, String address, String city,
                             String state, String telephone, String email) {
         try {
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=? WHERE id=?");
             stmt.setString(1, lastName);
             stmt.setString(2, firstName);
             stmt.setString(3, mi);
             stmt.setString(4, address);
             stmt.setString(5, city);
             stmt.setString(6, state);
             stmt.setString(7, telephone);
             stmt.setString(8, email);
             stmt.setString(9, id);

             int rowsAffected = stmt.executeUpdate();
             System.out.println(rowsAffected + " row(s) updated.");

             stmt.close();
         } catch (SQLException e) {
             System.err.println("Error executing updateStaff query.");
             e.printStackTrace();
         }
     }

     public static void main(String[] args) {
         Main manager = new Main();

         // Example usage
         manager.viewStaff("123456789");
         manager.insertStaff("123456789", "Doe", "John", "M", "123 Main St", "Anytown", "CA", "1234567890",
                 "john.doe@example.com");
         manager.updateStaff("123456789", "Doe", "Jane", "M", "123 Main St", "Anytown", "CA", "1234567890",
                 "jane.doe@example.com");
     }
 }
