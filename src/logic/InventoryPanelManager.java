package logic;

import panels.*;


import javax.swing.*;
import java.awt.*;

// Inventory Panel Manager Class

/**
 * Panel manager class which is responsible for switching between the screens
 */
public class InventoryPanelManager extends JPanel {
    //card layout used to layer the Panels
    private final CardLayout cardLayout;

    public InventoryPanelManager() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

//        create all the panel objects
        home homePanel = new home();
        Orders ordersPanel = new Orders();
        sales salesPanel = new sales();
        Inventory inventoryPanel = new Inventory();
        settings settingsPanel = new settings();


//      add the objects to the manager
        add(homePanel, "home");
        add(ordersPanel,"orders");
        add(salesPanel, "sales");
        add(inventoryPanel,"inventory");
        add(settingsPanel,"settings");
    }

    // Method to show a specific panel

    /**
     *
     * @param panelName - Insert the name of the panel to be shown if added to the manager
     */
    public void showPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
}