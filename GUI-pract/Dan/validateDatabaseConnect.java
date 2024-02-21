// the following class, while sharing the same functionalility as DataBaseConnector, will take in username and password in its constructor

package Dan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector2 {
    //because for all of my parts of this program I am required to connect to the database I will make this class
    //which can be called upon to do it instead of repeating the same code in each file

    private static final String URL = "jdbc:mysql://localhost:3306/Inventory";
    private static final String USER = null;
    private static final String PASSWORD = null;

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Method to establish a database connection
    public static Connection connect(String USER, String PASSWORD) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your project's needs
            return null;
        }
    }

    // Method to close the database connection
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}