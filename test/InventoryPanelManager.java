
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

// Inventory Panel Manager Class
public class InventoryPanelManager extends JPanel {
    private CardLayout cardLayout;

    public InventoryPanelManager() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        home homePanel = new home();
        Orders ordersPanel = new Orders();
        sales salesPanel = new sales();
        Inventory inventoryPanel = new Inventory();



        add(homePanel, "home");
        add(ordersPanel,"orders");
        add(salesPanel, "sales");
        add(inventoryPanel,"inventory");
    }

    // Method to show a specific panel
    public void showPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
}