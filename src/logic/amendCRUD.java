package logic;

import dbCon.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class amendCRUD {

    public amendCRUD() {}

    /**
     * Method for updating the database.
     *
     * @param tableName the name of the table to update
     * @param columns   an array of column names to update
     * @param values    an array of values corresponding to the columns
     * @param condition the condition to specify which rows to update
     */
    public void amendIntoTable(String tableName, String[] columns, Object[] values, String condition) {
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try {
            // Get connection from the DatabaseConnector
            connection = DatabaseConnector.connect();

            // Using StringBuilder to dynamically update necessary query data instead of hard coding names
            StringBuilder queryBuilder = new StringBuilder("UPDATE ")
                    .append(tableName)
                    .append(" SET ");

            // Building the SET clause for the update statement
            for (int j = 0; j < columns.length; j++) {
                queryBuilder.append(columns[j]).append(" = ?");
                if (j < columns.length - 1) {
                    queryBuilder.append(", ");
                }
            }

            // Appending the WHERE clause for specifying condition
            queryBuilder.append(" WHERE ").append(condition);

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
                    pstat.setDate(j + 1, (Date) values[j]);
                }
            }

            // Execute the update statement
            i = pstat.executeUpdate();
            System.out.println(i + " record(s) successfully updated in " + tableName + ".");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                // Disconnect from the DatabaseConnector
                DatabaseConnector.disconnect();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Method to update orders table.
     *
     * @param Order_ID     the order ID to update
     * @param Emp_ID       the employee ID to update
     * @param Stock_ID     the stock ID to update
     * @param date         the order date to update
     * @param TotalCost    the total cost to update
     * @param PaymentStat  the payment status to update
     * @param delivery     the estimated delivery date to update
     */
    public void amendIntoOrders(int Order_ID,int Emp_ID,int Stock_ID,Date date,BigDecimal TotalCost, String PaymentStat, Date delivery ){
        String[] columns = {"Emp_ID","Stock_ID","OrderDate","TotalCost","PaymentStatus","Est_Delivery"};
        Object[] values = {Emp_ID,Stock_ID,date,TotalCost,PaymentStat,delivery};
        String condition = "Order_ID =" + Order_ID; // Define appropriate condition
        amendIntoTable("Orders", columns, values, condition);
    }

    /**
     * Method to update stock items.
     *
     * @param product_ID   the product ID to update
     * @param name         the name to update
     * @param quantity     the quantity to update
     * @param unitPrice    the unit price to update
     * @param salePrice    the sale price to update
     * @param supplierId   the supplier ID to update
     * @param aisleNum     the aisle number to update
     */
    public void amendStockItems(int product_ID,String name, int quantity, BigDecimal unitPrice, BigDecimal salePrice, int supplierId, int aisleNum) {
        String[] columns = {"Product_ID","Name", "quantity_in_stock", "unit_price", "sale_price", "supplier_ID", "Aisle_num"};
        Object[] values = {product_ID,name, quantity, unitPrice, salePrice, supplierId, aisleNum};
        String condition = "product_ID =" + product_ID;
        amendIntoTable("Stock_Items",columns,values,condition);
    }
}
