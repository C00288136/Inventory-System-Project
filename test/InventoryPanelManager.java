
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

        add(homePanel, "home");
        add(ordersPanel,"orders");
    }

    // Method to show a specific panel
    public void showPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
}