import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    //because for all of my parts of this program I am required to connect to the database I will make this class
    //which can be called upon to do it instead of repeating the same code in each file

    private static final String URL = "jdbc:mysql://localhost:";
    private  static final String db_Name = "inventory";
    private static final String USER = "root";
<<<<<<< HEAD
    private static final String PASSWORD = "";
=======

//    private static final String PASSWORD = "root";
     private static final String PASSWORD = ""; // no password on this version of the db





>>>>>>> 19a947f2c5d868e29a135152737c677257675234

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
                // Handle the exception according to your project's needs
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
            // Handle the exception
        }
    }
}