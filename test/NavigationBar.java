
import javax.swing.*;

// Navigation Bar Class
public class NavigationBar extends JPanel {
    private InventoryPanelManager panelManager;

    public NavigationBar(InventoryPanelManager panelManager) {
        this.panelManager = panelManager;
        // Add buttons and set up action listeners
    }

    public NavigationBar() {

    }

    // Method to add panels to switch between
    public void addPanel(String panelName, JPanel panel) {
        // Add panel to switch between
    }
    
    // Method to handle button click events and switch panels
    private void switchPanel(String panelName) {
        panelManager.showPanel(panelName);
    }
}