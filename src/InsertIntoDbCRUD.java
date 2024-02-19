import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntoDbCRUD {

    private DatabaseConnector databaseConnector;

    public InsertIntoDbCRUD(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public InsertIntoDbCRUD() {}

    public void insertIntoTable(String tableName, String[] columns, Object[] values) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // Get connection from the DatabaseConnector
            connection = databaseConnector.connect();

            // Create Prepared Statement for inserting data into the specified table
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(" (");

            for (int j = 0; j < columns.length; j++) {
                queryBuilder.append(columns[j]);
                if (j < columns.length - 1) {
                    queryBuilder.append(", ");
                }
            }

            queryBuilder.append(") VALUES (");

            for (int j = 0; j < values.length; j++) {
                queryBuilder.append("?");
                if (j < values.length - 1) {
                    queryBuilder.append(", ");
                }
            }

            queryBuilder.append(")");

            String query = queryBuilder.toString();
            pstat = connection.prepareStatement(query);

            // Set values for each parameter
            for (int j = 0; j < values.length; j++) {
                if (values[j] instanceof String) {
                    pstat.setString(j + 1, (String) values[j]);
                } else if (values[j] instanceof Integer) {
                    pstat.setInt(j + 1, (Integer) values[j]);
                } else if (values[j] instanceof Long) {
                    pstat.setLong(j + 1, (Long) values[j]);
                } else if (values[j] instanceof BigDecimal) {
                    pstat.setBigDecimal(j + 1, (BigDecimal) values[j]);
                } else if (values[j] instanceof Date){
                    {pstat.setDate(j + 1, (Date) values[j]);}
                }
            }

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

    public void insertIntoEmployees(String lastName, String firstName, int age, long phoneNum, String address) {
        String[] columns = {"LastName", "FirstName", "Age", "Phone_No", "Address"};
        Object[] values = {lastName, firstName, age, phoneNum, address};
        insertIntoTable("Employees", columns, values);
    }

    public void insertIntoStockItems(String name, int quantity, BigDecimal unitPrice, BigDecimal salePrice, int supplierId, int aisleNum) {
        String[] columns = {"Name", "Quantity", "UnitPrice", "SalePrice", "SupplierId", "AisleNum"};
        Object[] values = {name, quantity, unitPrice, salePrice, supplierId, aisleNum};
        insertIntoTable("Stock_Items", columns, values);
    }

    public void insertIntoOrders(int Emp_ID,int Stock_ID,Date date,BigDecimal TotalCost, String PaymentStat, Date delivery ){
        String[] columns = {"Emp_ID","Stock_ID","OrderDate","TotalCost","PaymentStatus","Est_Delivery"};
        Object[] values = {Emp_ID,Stock_ID,date,TotalCost,PaymentStat,delivery};
        insertIntoTable("Orders", columns, values);
    }
}
