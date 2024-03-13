


// Main Class

import java.awt.BorderLayout;

import javax.swing.*;

public class Driver {
/*    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Inventory Monitoring Program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            NavigationBar navBar = new NavigationBar();
            frame.add(navBar, BorderLayout.WEST);

            InventoryPanelManager panelManager = new InventoryPanelManager();
            frame.add(panelManager, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }*/

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Monitoring Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        InventoryPanelManager panelManager = new InventoryPanelManager();
        NavigationBar navBar = new NavigationBar(panelManager);

        panelManager.showPanel("orders");
        frame.add(navBar, BorderLayout.WEST);
        frame.add(panelManager, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
