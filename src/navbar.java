

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class navbar extends JPanel {



    JButton home = new JButton("Home");
    JButton orders = new JButton("Orders");
    JButton sales = new JButton("Sales");
    JButton salesReport = new JButton("Sales Report");
    JButton Inventory = new JButton("Inventory");
    JButton settings = new JButton("Settings");

    JPanel TablePanel = new JPanel();

    public navbar(){

    setBackground(Color.decode("#8764EC"));
    JButton[] navbuttons = {home, orders, sales, salesReport, Inventory, settings};
    int navbuttonWidth = 120;
    int navbuttonHeight = 40;
    int navverticalGap = 10;
    setLayout(new GridLayout(0,1));


    for (JButton button : navbuttons) {
        button.setPreferredSize(new Dimension(navbuttonWidth, navbuttonHeight));
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setForeground(Color.WHITE);

        add(button);

    }

    //Action Listener

Inventory.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Create and show JTableExample in the body panel
            JTableExample table = new JTableExample("stock_items");

            TablePanel.removeAll(); // Clear existing components in TablePanel
            TablePanel.add(table);
            TablePanel.revalidate(); // Refresh the layout
            TablePanel.repaint(); // Repaint the panel
         

       //TODO implement JTableExample class to appear as panel in gui
    }
});

sales.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
            // Create and show JTableExample in the body panel
            JTableExample table = new JTableExample("sales");

            TablePanel.removeAll(); // Clear existing components in TablePanel
            TablePanel.add(table);
            TablePanel.revalidate(); // Refresh the layout
            TablePanel.repaint(); // Repaint the panel
            

            //TODO implement JTableExample class to appear as panel in gui
    }
});

orders.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Create and show JTableExample in the body panel
            JTableExample table = new JTableExample("orders");

            TablePanel.removeAll(); // Clear existing components in TablePanel
            TablePanel.add(table);
            TablePanel.revalidate(); // Refresh the layout
            TablePanel.repaint(); // Repaint the panel
            
         

       //TODO implement JTableExample class to appear as panel in gui
    }
});

setVisible(true);

}
}
