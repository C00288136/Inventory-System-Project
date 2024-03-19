


// Main Class

import java.awt.BorderLayout;

import javax.swing.*;

public class Driver {



    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Monitoring Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        InventoryPanelManager panelManager = new InventoryPanelManager();
        NavigationBar navBar = new NavigationBar(panelManager);
        header head = new header();


        panelManager.showPanel("home");
        frame.add(navBar, BorderLayout.WEST);
        frame.add(panelManager, BorderLayout.CENTER);
        frame.add(head,BorderLayout.NORTH);

        frame.setVisible(true);
    }

}
