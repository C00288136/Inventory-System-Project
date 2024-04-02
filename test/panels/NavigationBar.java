package panels;

import logic.InventoryPanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationBar extends JPanel {
    private InventoryPanelManager panelManager;

    JButton home = new JButton("Home");
    JButton orders = new JButton("Orders");
    JButton sales = new JButton("Sales");
    JButton inventory = new JButton("Inventory");
    JButton settings = new JButton("Settings");

    ImageIcon[] navIcons = {
        new ImageIcon("assets/home.png"),
        new ImageIcon("assets/orders.png"),
        new ImageIcon("assets/sales.png"),
        new ImageIcon("assets/inventory.png"),
        new ImageIcon("assets/settings.png"),
    };

    public NavigationBar(InventoryPanelManager panelManager) {
        this.panelManager = panelManager;
        setBackground(Color.decode("#8764EC"));
        setLayout(new GridLayout(0, 1));

        JButton[] navbuttons = {home, orders, sales, inventory, settings};
        int navbuttonWidth = 140;
        int navbuttonHeight = 40;

        int i = 0;
        for (JButton button : navbuttons) {
            button.setPreferredSize(new Dimension(navbuttonWidth, navbuttonHeight));
            button.setContentAreaFilled(false);
            button.setBorderPainted(true);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.setForeground(Color.WHITE);
            button.setIcon(navIcons[i]);
            add(button);
            i++;
        }

        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel("home"); // Assuming "Home" is the panel name
                System.out.println("switched to home");
            }
        });

        orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel("orders");
                System.out.println("switched to orders");
            }
        });

        sales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel("sales");
                System.out.println("switched to sales");
            }
        });
        // Add action listeners for other buttons

        inventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel("inventory");
                System.out.println("switched to inventory");
            }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel("settings");
                System.out.println("switched to settings");
            }
        });
    }

    // Method to handle button click events and switch panels
    private void switchPanel(String panelName) {
        panelManager.showPanel(panelName);
    }
}
