package Dan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JTableExample extends JFrame {
    JFrame tableFrame;
    JTable table;

    JTableExample(String tableName) {
        tableFrame = new JFrame();
        tableFrame.setTitle("Table");

        // Use JTableData to dynamically fetch data from the database
        JTableData jTableData = new JTableData("sales");
        DefaultTableModel model = jTableData.tableModel;

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        tableFrame.add(sp);
        tableFrame.setSize(500, 200);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Instantiate the JTableExample class with the desired table name
        new JTableExample("Employees");
    }
}
