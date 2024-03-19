import javax.swing.*;
import java.awt.*;

public class Orders extends JPanel {

    JButton addOrder = new JButton("Add Order");
    JButton deleteOrder = new JButton("Delete Order");
    JButton amendOrder = new JButton("Amend Order");

    JTableExample table = new JTableExample("Orders");

    int tablewidth = 700;
    int tableheight = 400;
    int buttonPanelHeight = 50;

    // Constructor
    public Orders() {
        setLayout(new BorderLayout());

        // Add the table to the center of the Orders panel
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding

        // Apply some styling to buttons
        addOrder.setPreferredSize(new Dimension(120, 30));
        deleteOrder.setPreferredSize(new Dimension(120, 30));
        amendOrder.setPreferredSize(new Dimension(120, 30));
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        addOrder.setFont(buttonFont);
        deleteOrder.setFont(buttonFont);
        amendOrder.setFont(buttonFont);

        // Add buttons to buttonPanel
        buttonPanel.add(addOrder);
        buttonPanel.add(deleteOrder);
        buttonPanel.add(amendOrder);

        // Add the button panel to the bottom of the Orders panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Orders Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Orders());
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame on screen
            frame.setVisible(true);
        });
    }
}


/*
 * import javax.swing.*;
import java.awt.*;

public class Orders extends JPanel {

    JPanel orderTable = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton addOrder = new JButton("Add Order");
    JButton deleteOrder = new JButton("Delete Order");
    JButton amendOrder = new JButton("Amend Order");

    JTableExample table = new JTableExample("Orders");

    int tablewidth = 700;
    int tableheight = 400;
    int buttonPanelWidth = 700;
    int buttonPanelHeight = 50;

    // Constructor
    public Orders() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set layout for buttonPanel
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns, horizontal gap of 10

        // Add buttons to buttonPanel
        buttonPanel.add(addOrder);
        buttonPanel.add(deleteOrder);
        buttonPanel.add(amendOrder);

        // Set buttonPanel size and position
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        add(buttonPanel, gbc);

        // Add the table to the center of the Orders panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(table), gbc);

        // Set preferred size of the Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Orders Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Orders());
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame on screen
            frame.setVisible(true);
        });
    }
}
 */
/* sales salesreport  inventory settings */
