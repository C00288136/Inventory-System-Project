package Dan;

import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class JTableData {

    Connection connection;
    ResultSetMetaData  dbMeta;
    DefaultTableModel tableModel;
    String tableName;

    public JTableData(String tableName) {
        this.tableName = tableName;
        connection = DatabaseConnector.connect();
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try {
            pstat = connection.prepareStatement("Select * FROM "+tableName+";");
            resultSet = pstat.executeQuery();
            tableModel = new DefaultTableModel();
            dbMeta = resultSet.getMetaData();
            int ColumnNo = dbMeta.getColumnCount();

            for (int i=1; i<ColumnNo; i++)
            {
                tableModel.addColumn(dbMeta.getColumnName(i));
            }

            while(resultSet.next())
            {
                Object[] row = new Object[ColumnNo];
                for (int i=1; i<ColumnNo; i++)
                {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
        } 
        catch(SQLException sqlException) {
            sqlException . printStackTrace();
        }
        finally {
            try {
                pstat . close();
                DatabaseConnector.disconnect();
            }
            catch (Exception exception) {
                exception . printStackTrace () ;
            }
        }
        /*try {
            dbMeta = connection.getMetaData();
            resultSet = dbMeta.getColumns(null, null, tableName, null);

            tableModel = new DefaultTableModel();

            int columnCount = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            // Fetch data from the database
            try {
                PreparedStatement pstat = connection.prepareStatement("SELECT * FROM " + tableName);
                ResultSet dataResultSet = pstat.executeQuery();

                while (dataResultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = dataResultSet.getObject(i);
                    }
                    tableModel.addRow(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
