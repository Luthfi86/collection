import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/u133239201_....";
    String username = "u133239201_....";
    String password = ";ZG:0Tc~";
    try (Connection con = DriverManager.getConnection(url, username, password)) {
      System.out.println("Database connection successful.");
    } catch (SQLException e) {
      System.out.println("Database connection failed: " + e.getMessage());
    }
  }
}
