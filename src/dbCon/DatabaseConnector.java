/**
 * Package used for storing the database connector
 */
package dbCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connector class
 */
public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:";
    private  static final String db_Name = "inventory";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static int[] PORTS = {3306,8889};//we have different ports for the mamp and xampp sever

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Method to establish a database connection
    public static Connection connect() {
        for (int port : PORTS) {
            try {
                String url = URL + port + "/" + db_Name;
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, USER, PASSWORD);
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Failed to connect on port: " + port);
                e.printStackTrace();
            }
        }
        System.out.println("Failed to connect to database!");
                return null;
    }

    // Method to close the database connection
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}