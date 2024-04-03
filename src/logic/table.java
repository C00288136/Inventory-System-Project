package logic;

import dbCon.DatabaseConnector;
import logic.DeleteCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class table extends JPanel {
    JTable table;
    Connection connection;
    ResultSetMetaData dbMeta;
    DefaultTableModel tableModel;
    String tableName;
    private Object selectedPrimaryKey;


    public table(String tableName) {
        // Use JTableData to dynamically fetch data from the database
        this.tableName = tableName;
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        // Set preferred size for the table
//        scrollpane adjusts to the table
        table.setPreferredScrollableViewportSize(new Dimension(700,600));

        // Call fetchData method on the instance of JTableExample
        fetchData();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedrow = table.getSelectedRow();
                if (SwingUtilities.isLeftMouseButton(e)){
                    selectedPrimaryKey = tableModel.getValueAt(selectedrow,0);
                    System.out.println(selectedPrimaryKey);
                }

            }
        });
    }

    public void fetchData() {
        connection = DatabaseConnector.connect();
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
    
        try {
            pstat = connection.prepareStatement("SELECT * FROM " + tableName);
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

    public Object getSelectedPrimaryKey(){
        return selectedPrimaryKey;
    }
    public void deleteEntry(Object row){
        try {
            Connection connection = DatabaseConnector.connect();
            String PriamryKey = table.getColumnName(0);
            int Row = (int) row;
            Object primaryKeyValue = tableModel.getValueAt(Row,0);
            String sql = "DELETE FROM" + tableName + "WHERE " + PriamryKey + " = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setObject(1,primaryKeyValue);
            int affectedRows = pstat.executeUpdate();
            if (affectedRows > 0 ){
                JOptionPane.showMessageDialog(null,"Entry has been deleted");

                fetchData();
            }
            else {
                JOptionPane.showMessageDialog(null,"Failed to delete entry");
            }
            pstat.close();
            DatabaseConnector.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
