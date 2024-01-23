import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveData {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Retrieve data from the student table
            retrieveStudentData(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void retrieveStudentData(Connection connection) throws SQLException {
        String sql = "SELECT id, name, age FROM student";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                // Do something with the retrieved data
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        }
    }
}
