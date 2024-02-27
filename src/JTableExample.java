/*
 * JTableExample class used to display a JFrame and JTable populated from a database
 */



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JTableExample extends JFrame {
    JFrame tableFrame;
    JTable table;

    public JTableExample(String tableName) {
        tableFrame = new JFrame();
        tableFrame.setTitle("Table");

        // Use JTableData to dynamically fetch data from the database
        JTableData jTableData = new JTableData(tableName);
        DefaultTableModel model = jTableData.tableModel;

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        tableFrame.add(sp);
        tableFrame.setSize(500, 200);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void disposeWindow(){
        System.out.println("dispose called");
        tableFrame.dispose();
    }

}
