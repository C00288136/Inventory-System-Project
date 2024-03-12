


// Main Class

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Driver {
    public static void main(String[] args) {
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
    }
}
