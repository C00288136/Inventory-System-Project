import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JTableExample extends JPanel {
    JTable table;
    Connection connection;
    ResultSetMetaData dbMeta;
    DefaultTableModel tableModel;
    String tableName;

    public JTableExample(String tableName) {
        // Use JTableData to dynamically fetch data from the database
        this.tableName = tableName;
        //JTableData jTableData = new JTableData(tableName);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        // Set preferred size for the JScrollPane to match the preferred size of the table
        sp.setPreferredSize(table.getPreferredScrollableViewportSize());
        fetchData();
    }

    public void fetchData() {
        connection = DatabaseConnector.connect();
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
    
        try {
            pstat = connection.prepareStatement("SELECT * FROM " +tableName);
            resultSet = pstat.executeQuery();
            dbMeta = resultSet.getMetaData();
            int columnCount = dbMeta.getColumnCount();
    
            // Clear existing data from the table model
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
    
            // Add columns to the table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(dbMeta.getColumnName(i));
            }
    
            // Add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstat != null) pstat.close();
                DatabaseConnector.disconnect();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    
}