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

    //method for inserting into the database
    public void insertIntoTable(String tableName, String[] columns, Object[] values) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // Get connection from the DatabaseConnector
            connection = databaseConnector.connect();

            //using string builder to dynamically insert necessary query data instead of hard coding names
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

            //what both for loops do is loop through the columns and the values arrays and append them dynamically into the sql query
            // which can then be executed

            queryBuilder.append(")");

            String query = queryBuilder.toString();
            pstat = connection.prepareStatement(query);

            // Set values for each parameter
            //because the different tables have different values and in different row this for loop is
            //checking what type of value the current is and if it is not what it should be it gets cast to that type
            //j + 1 is used because sql indexing starts at 1

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

    //because as mentioned values are different for each table I made separate methods for inserting into each table
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
        String[] columns = {"Emp_ID","Stock_ID","OrderDate","TotalCost","PaymentStatus","Est.Delivery"};
        Object[] values = {Emp_ID,Stock_ID,date,TotalCost,PaymentStat,delivery};
        insertIntoTable("panels.Orders", columns, values);
    }
}
