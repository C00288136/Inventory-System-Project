package logic;

import dbCon.DatabaseConnector;

import javax.swing.*;
import java.sql.*;

public class DeleteCRUD {

    public DeleteCRUD() {}

    public void deleteFromTable(String tableName, int ID) {
        Connection connection = null;

        try {
            connection = DatabaseConnector.connect();
            DatabaseMetaData metaData = connection.getMetaData();

            //used metadata to dynamically write the query for delecting the user by their primary key
            String idColumnName = null;
            ResultSet resultSet = metaData.getPrimaryKeys(null, null, tableName);

            if (resultSet.next()) {
                idColumnName = resultSet.getString("COLUMN_NAME");
            } else {
                System.out.println("Table " + tableName + " doesn't have a primary key");
                return;
            }

            String deleteQuery = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, ID);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Record deleted successfully.");
                } else {
                    System.out.println("Record with ID " + ID + " not found.");
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null,"Cannot delete record due to a foreign key constraint");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
