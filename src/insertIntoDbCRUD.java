import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertIntoDbCRUD {

    private DatabaseConnector databaseConnector;

    public insertIntoDbCRUD(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public insertIntoDbCRUD() {

    }

    public void insertIntoEmployees(String lastName, String firstName, int age, long phoneNum, String address) {
        insert("Employees", lastName, firstName, age, phoneNum, address);
    }

    public void insertIntoStockItems(String name, int quantity, BigDecimal unitPrice, BigDecimal salePrice, int supplierId, int aisleNum) {
        insert("Stock_Items", name, quantity, unitPrice, salePrice, supplierId, aisleNum);
    }

    private void insert(String tableName, String column1, String column2, int value3, long value4, String value5) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // Get connection from the DatabaseConnector
            connection = databaseConnector.connect();

            // Create Prepared Statement for inserting data into the specified table
            String query = String.format("INSERT INTO %s (Column1, Column2, Column3, Column4, Column5) VALUES (?,?,?,?,?)", tableName);
            pstat = connection.prepareStatement(query);
            pstat.setString(2, column1);
            pstat.setString(1, column2);
            pstat.setInt(3, value3);
            pstat.setLong(4, value4);
            pstat.setString(5, value5);

            // Insert data into the table
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully added to " + tableName + ".");
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

    private void insert(String tableName, String column1, int value2, BigDecimal value3, BigDecimal value4, int value5, int value6) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // Get connection from the DatabaseConnector
            connection = databaseConnector.connect();

            // Create Prepared Statement for inserting data into the specified table
            String query = String.format("INSERT INTO %s (Column1, Column2, Column3, Column4, Column5, Column6) VALUES (?,?,?,?,?,?)", tableName);
            pstat = connection.prepareStatement(query);
            pstat.setString(1, column1);
            pstat.setInt(2, value2);
            pstat.setBigDecimal(3, value3);
            pstat.setBigDecimal(4, value4);
            pstat.setInt(5, value5);
            pstat.setInt(6, value6);

            // Insert data into the table
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully added to " + tableName + ".");
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
