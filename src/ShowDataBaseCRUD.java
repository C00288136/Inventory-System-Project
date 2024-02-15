import java.math.BigDecimal;
import java.sql.*;

public class ShowDataBaseCRUD {

    private DatabaseConnector databaseConnector;

    public ShowDataBaseCRUD(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public ShowDataBaseCRUD() {

    }

    public void showTable(String tableName) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement pstat = null;
        String ShowTable = "SELECT * FROM " + tableName;

        try {
            // Get connection from the DatabaseConnector
            connection = databaseConnector.connect();

            PreparedStatement preparedStatement = connection.prepareStatement(ShowTable);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numuberofColumns = metaData.getColumnCount();
            System.out.println("Employee Table");
            for (int i=1; i<numuberofColumns;i++)
                System.out.print(metaData.getColumnName(i) + "\t" );
            System.out.println();



            while(resultSet.next()){
                for (int i = 1; i < numuberofColumns;i++){
                    System.out.print(resultSet.getObject(i) + "\t\t");
                }
                System.out.println();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                // Disconnect from the DatabaseConnector
                databaseConnector.disconnect();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}
