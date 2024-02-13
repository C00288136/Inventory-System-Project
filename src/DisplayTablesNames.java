import java.sql.*;

public class DisplayTablesNames {
    private static final String URL = "jdbc:mysql://localhost:3306/Inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void printTableNames() {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            System.out.println("Table Names:");
            int count = 1;
            int limit = 5;
            while (resultSet.next() && count < limit) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(count+". " + tableName);
                count++;
            }

            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

