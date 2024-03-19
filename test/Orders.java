

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;



public class Orders extends JPanel {

    JButton addOrder = new JButton("Add Order");
    JButton deleteOrder = new JButton("Delete Order");
    JButton amendOrder = new JButton("Amend Order");
    JButton insert = new JButton("Insert Data");
    JButton delete = new JButton("Delete");
    JTextField stockIDField  = new JTextField();
    JTextField empIDField  = new JTextField();
    JTextField orderDateField  = new JTextField();
    JTextField totalCostField  = new JTextField();
    JTextField PaymentStatusField  = new JTextField();
    JTextField deliveryDateField = new JTextField();
    InsertIntoDbCRUD crud = new InsertIntoDbCRUD();


    JTableExample table = new JTableExample("Orders");

    int tablewidth = 800;
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

        //Action Listers for buttons

        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addOrderFrame = new JFrame();

                Container contentPane = addOrderFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5)); // 5 rows, 2 columns
        
                contentPane.add(new JLabel("Employee ID:"));
                contentPane.add(empIDField);
                contentPane.add(new JLabel("Stock ID:"));
                contentPane.add(stockIDField);
                contentPane.add(new JLabel("Order Date:"));
                contentPane.add(orderDateField);
                contentPane.add(new JLabel("Total Cost:"));
                contentPane.add(totalCostField);
                contentPane.add(new JLabel("Payment Status:"));
                contentPane.add(PaymentStatusField);
                contentPane.add(new JLabel("DeliveryDate"));
                contentPane.add(deliveryDateField);
                contentPane.add(insert);


                insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int Emp_ID = Integer.parseInt(empIDField.getText());
                        int Stock_ID = Integer.parseInt(stockIDField.getText());
                        BigDecimal TotalCost = new BigDecimal(totalCostField.getText());
                        String PaymentStat = PaymentStatusField.getText();
                        Date orderDate = Date.valueOf(orderDateField.getText());
                        Date deliveryDate = Date.valueOf(deliveryDateField.getText());
                        
                        crud.insertIntoOrders(Emp_ID,Stock_ID,orderDate,TotalCost,PaymentStat,deliveryDate);
                    }
                });



        
                addOrderFrame.pack();
                addOrderFrame.setLocationRelativeTo(null); // Center the frame on screen
                addOrderFrame.setVisible(true);
            }
        });

        deleteOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame deleteOrderFrame = new JFrame();

                Container contentPane = deleteOrderFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5));

                contentPane.add(new JLabel("Employee ID:"));
                contentPane.add(empIDField);
                contentPane.add(new JLabel("Stock ID:"));
                contentPane.add(stockIDField);
                contentPane.add(new JLabel("Order Date:"));
                contentPane.add(orderDateField);
                contentPane.add(new JLabel("Total Cost:"));
                contentPane.add(totalCostField);
                contentPane.add(new JLabel("Payment Status:"));
                contentPane.add(PaymentStatusField);
                contentPane.add(new JLabel("DeliveryDate"));
                contentPane.add(deliveryDateField);
                contentPane.add(delete);

                delete.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                    int Emp_ID = Integer.parseInt(empIDField.getText());
                    int Stock_ID = Integer.parseInt(stockIDField.getText());
                    BigDecimal TotalCost = new BigDecimal(totalCostField.getText());
                    String PaymentStat = PaymentStatusField.getText();
                    Date orderDate = Date.valueOf(orderDateField.getText());
                    Date deliveryDate = Date.valueOf(deliveryDateField.getText());

                    DeleteCRUD deleteCRUD = new DeleteCRUD();
                    deleteCRUD.deleteFromTable(PaymentStat, Stock_ID);
                   } 
                });

                deleteOrderFrame.pack();
                deleteOrderFrame.setLocationRelativeTo(null);
                deleteOrderFrame.setVisible(true);
            }
        });
        

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
