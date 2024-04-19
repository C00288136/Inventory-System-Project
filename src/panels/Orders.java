package panels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JDateChooser;

import dbCon.DatabaseConnector;
import logic.DeleteCRUD;
import logic.InsertIntoDbCRUD;
import logic.amendCRUD;
import logic.table;

public class Orders extends JPanel {

    JButton addOrder = new JButton("Add Order");
    JButton deleteOrder = new JButton("Delete Order");
    JButton amendOrder = new JButton("Amend Order");
    JButton insert = new JButton("Insert Data");
    JButton delete = new JButton("Delete");
    JButton amend = new JButton("Amend");
    JComboBox<Integer> empIDField;
    JTextField tableNameField = new JTextField();
    JTextField stockIDField  = new JTextField();
    JDateChooser orderDateField  = new JDateChooser();
    JTextField orderIdField = new JTextField();
    JTextField totalCostField  = new JTextField();
    JTextField PaymentStatusField  = new JTextField();
    JDateChooser deliveryDateField = new JDateChooser();
    InsertIntoDbCRUD crud = new InsertIntoDbCRUD();
    DeleteCRUD deleteCrud = new DeleteCRUD();
    amendCRUD amendCrud = new amendCRUD();

    Integer[] empIds = {100,101,102,103,104,105,106,107,108,109,110};


    table table = new table("Orders");

    int tablewidth = 800;
    int tableheight = 400;
    int buttonPanelHeight = 50;

    // Constructor
    public Orders() {
        setLayout(new BorderLayout());

        // Add the table to the center of the panels.Orders panel
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

        // ADD OPERATIONS
        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addOrderFrame = new JFrame();
                orderDateField.setDateFormatString("yyyy-MM-dd");
                deliveryDateField.setDateFormatString("yyyy-MM-dd");

                Container contentPane = addOrderFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5)); // 5 rows, 2 columns
                empIDField = new JComboBox<>(empIds);

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

                        Object selectedEmpId = empIDField.getSelectedItem();
                        Integer Emp_ID = (Integer) selectedEmpId;
                        int Stock_ID = Integer.parseInt(stockIDField.getText());
                        BigDecimal TotalCost = new BigDecimal(totalCostField.getText());
                        String PaymentStat = PaymentStatusField.getText();
                        java.sql.Date orderDate = new java.sql.Date(orderDateField.getDate().getTime());
                        java.sql.Date deliveryDate = new java.sql.Date(deliveryDateField.getDate().getTime());

                        crud.insertIntoOrders(Emp_ID,Stock_ID,orderDate,TotalCost,PaymentStat,deliveryDate);
                        table.fetchData();
                    }
                });



                addOrderFrame.pack();
                addOrderFrame.setLocationRelativeTo(null); // Center the frame on screen
                addOrderFrame.setVisible(true);
            }
        });

        // DELETE OPERATIONS

        deleteOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object primarykey = table.getSelectedPrimaryKey();
                if (primarykey != null) {
                    int row = (int) primarykey;
                    int dialogBox = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this record", "Warning", dialogBox);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteCrud.deleteFromTable("Orders",row);
                        System.out.println(row + " Deleted!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select a row to delete.");
                }
                table.fetchData();
            }

        });


        //AMEND OPERATIONS

        amendOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame amendOrderFrame = new JFrame();
                Container contentPane = amendOrderFrame.getContentPane();
                contentPane.setLayout(new GridLayout(0, 2, 5, 5));
                orderDateField.setDateFormatString("yyyy-MM-dd");
                deliveryDateField.setDateFormatString("yyyy-MM-dd");
                empIDField = new JComboBox<>(empIds);

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
                contentPane.add(amend);

                Object primarykey = table.getSelectedPrimaryKey();
                if (primarykey != null) {
                    int id = (int) primarykey;

                    Connection con = DatabaseConnector.connect();
                    try {
                        PreparedStatement pstat = con.prepareStatement("SELECT * FROM Orders where Order_ID = ?");
                        pstat.setInt(1,id);

                        ResultSet rs = pstat.executeQuery();

                        while (rs.next()) {
                            int Order_ID = rs.getInt("Order_ID");
                            int Emp_ID = rs.getInt("Emp_ID");
                            int Stock_ID = rs.getInt("Stock_ID");
                            Date Orderdate = rs.getDate("OrderDate");
                            BigDecimal TotalCost = rs.getBigDecimal("TotalCost");
                            String PaymentStat = rs.getString("PaymentStatus");
                            Date delivery = rs.getDate("Est_Delivery");

                            empIDField.setSelectedItem(Emp_ID);
                            stockIDField.setText(String.valueOf(Stock_ID));
                            totalCostField.setText(String.valueOf(TotalCost));
                            PaymentStatusField.setText(PaymentStat);

                            // Formatting dates
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Choose your desired date format
                            String orderDateString = dateFormat.format(Orderdate);
                            String deliveryDateString = dateFormat.format(delivery);

                            orderDateField.setDate(Orderdate);
                            deliveryDateField.setDate(delivery);
                        }
                        rs.close();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(getParent(),"Please select a entry to amend");
                }
                    amend.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Object selectedEmpId = empIDField.getSelectedItem();
                            Integer Emp_ID = (Integer) selectedEmpId;
                            int Stock_ID = Integer.parseInt(stockIDField.getText());
                            BigDecimal TotalCost = new BigDecimal(totalCostField.getText());
                            String PaymentStat = PaymentStatusField.getText();
                            java.sql.Date orderDate = new java.sql.Date(orderDateField.getDate().getTime());
                            java.sql.Date deliveryDate = new java.sql.Date(deliveryDateField.getDate().getTime());

                            int dialogBox = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure about the Details Entered?","Confirmation",dialogBox);
                            if (dialogResult == JOptionPane.YES_OPTION){
                                amendCRUD crud = new amendCRUD();
                                crud.amendIntoOrders((int) primarykey, Emp_ID, Stock_ID, orderDate, TotalCost, PaymentStat, deliveryDate);
                                table.fetchData();
                                JOptionPane.showMessageDialog(null,"Entry Details have been updated");
                            }

                        }
                    });



                amendOrderFrame.pack();
                amendOrderFrame.setLocationRelativeTo(null);
                amendOrderFrame.setVisible(true);
            }
        });


        // Add the button panel to the bottom of the panels.Orders panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the panels.Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));

    }

    //TODO: validate entered data and have relevant error handling, JComboBox, JCalender

    // Main method for testing

}
