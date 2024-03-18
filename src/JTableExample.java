import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableExample extends JPanel {
    JTable table;

    public JTableExample(String tableName) {
        // Use JTableData to dynamically fetch data from the database
        JTableData jTableData = new JTableData(tableName);
        DefaultTableModel model = jTableData.tableModel;

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        add(sp);

        // Set preferred size for the JScrollPane to match the preferred size of the table
        sp.setPreferredSize(table.getPreferredScrollableViewportSize());
    }

}
