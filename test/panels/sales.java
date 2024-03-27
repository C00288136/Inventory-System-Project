package panels;

import logic.JTableExample;

import javax.swing.*;
import java.awt.*;

public class sales extends JPanel {

    JTableExample table = new JTableExample("Sales");

    JButton addSale = new JButton("Add Sale");
    JButton deleteSale = new JButton("Delete Sale");
    JButton ammendSale = new JButton("Ammend Sale");
    JTextField empIDField = new JTextField();
    JTextField stockIDField = new JTextField();
    JTextField SaleDateField = new JTextField();
    JTextField TotalPriceField = new JTextField();
    JTextField QuantityField = new JTextField();

    int tablewidth = 700;
    int tableheight = 400;
    int buttonPanelHeight = 50;
    public sales(){
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        addSale.setPreferredSize(new Dimension(120, 30));
        deleteSale.setPreferredSize(new Dimension(120, 30));
        ammendSale.setPreferredSize(new Dimension(120, 30));

        buttonPanel.add(addSale);
        buttonPanel.add(deleteSale);
        buttonPanel.add(ammendSale);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the panels.Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));

    }

}
