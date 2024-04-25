package panels;

import Design.Themes;
import Validation.NumberOnlyTextField;
import com.toedter.calendar.JDateChooser;
import dbCon.DatabaseConnector;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * class for Orders panel
 */
public class Orders extends JPanel {

    JButton addOrder = new JButton("Add Order");
    JButton deleteOrder = new JButton("Delete Order");
    JButton amendOrder = new JButton("Amend Order");
    JButton insert = new JButton("Insert Data");
    JButton amend = new JButton("Amend");
    List<Integer> empIDs = homeLogic.fetchEmpID();
    Integer[] empIDArray = empIDs.toArray(new Integer[0]);
    JComboBox<Integer> empIDField;
    List<String> stockItems = homeLogic.fetchItemsFromStock();
    String[] stockItemsArray = stockItems.toArray(new String[0]);
    JComboBox<String> stockIDField;
    JDateChooser orderDateField  = new JDateChooser();
    JTextField totalCostField  = new JTextField();
    JComboBox<String> paymentStatusfield;
    String[] paymentStat = {"paid","pending"};
    JDateChooser deliveryDateField = new JDateChooser();
    InsertIntoDbCRUD crud = new InsertIntoDbCRUD();
    DeleteCRUD deleteCrud = new DeleteCRUD();


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
        Themes.applyButtonTheme(addOrder);
        Themes.applyButtonTheme(deleteOrder);
        Themes.applyButtonTheme(amendOrder);

        // Add buttons to buttonPanel
        buttonPanel.add(addOrder);
        buttonPanel.add(deleteOrder);
        buttonPanel.add(amendOrder);

        //Action Listeners for buttons

        // ADD OPERATIONS
        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addOrderFrame = new JFrame();
                addOrderFrame.setPreferredSize(new Dimension(300,400));
                orderDateField.setDateFormatString("yyyy-MM-dd");
                deliveryDateField.setDateFormatString("yyyy-MM-dd");
                stockIDField = new JComboBox<>(stockItemsArray);
                empIDField = new JComboBox<>(empIDArray);

                Container contentPane = addOrderFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5)); // 5 rows, 2 columns
                empIDField = new JComboBox<>(empIDArray);

                contentPane.add(new JLabel("Employee ID:"));
                contentPane.add(empIDField);
                contentPane.add(new JLabel("Stock ID:"));
                contentPane.add(stockIDField);
                contentPane.add(new JLabel("Order Date:"));
                contentPane.add(orderDateField);
                contentPane.add(new JLabel("Total Cost:"));
                contentPane.add(totalCostField);
                contentPane.add(new JLabel("Payment Status:"));
                paymentStatusfield = new JComboBox<>(paymentStat);
                contentPane.add(paymentStatusfield);
                contentPane.add(new JLabel("DeliveryDate"));
                contentPane.add(deliveryDateField);
                Themes.applyButtonTheme(insert);
                contentPane.add(insert);


                insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String OrderDateText = orderDateField.getDateFormatString();
                        String TotalCostText = totalCostField.getText();
                        String DeliveryDate = deliveryDateField.getDateFormatString();

                        if(OrderDateText.isEmpty()||TotalCostText.isEmpty()||DeliveryDate.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Please fill in all the fields","Error",JOptionPane.ERROR_MESSAGE);
                            return;//stops further execution if condition not met
                        }

                        if (!NumberOnlyTextField.isValidPrice(TotalCostText)) {
                            JOptionPane.showMessageDialog(null, "Total Cost Price must contain only numbers and periods", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further execution if condition not met
                        }

                        try {
                            Object selectedEmpId = empIDField.getSelectedItem();
                            Integer Emp_ID = (Integer) selectedEmpId;
                            String selectedStockID = (String) stockIDField.getSelectedItem();
                            String[] parts = selectedStockID.split(" - ");
                            int Stock_ID = Integer.parseInt(parts[0]);
                            BigDecimal TotalCost = new BigDecimal(totalCostField.getText());
                            Object payment = paymentStatusfield.getSelectedItem();
                            String PaymentStat = (String) payment;
                            java.sql.Date orderDate = new java.sql.Date(orderDateField.getDate().getTime());
                            java.sql.Date deliveryDate = new java.sql.Date(deliveryDateField.getDate().getTime());

                            crud.insertIntoOrders(Emp_ID,Stock_ID,orderDate,TotalCost,PaymentStat,deliveryDate);
                            JOptionPane.showMessageDialog(null, "Entry added to the Orders table");
                            addOrderFrame.dispose();
                        } catch (Exception exception){
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(null,"Error occurred while adding the Order","Error",JOptionPane.ERROR_MESSAGE);

                        }
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
                Object primarykey = table.getSelectedPrimaryKey();
                if (primarykey != null) {
                    int id = (int) primarykey;
                JFrame amendOrderFrame = new JFrame();
                amendOrderFrame.setPreferredSize(new Dimension(300,400));
                Container contentPane = amendOrderFrame.getContentPane();
                contentPane.setLayout(new GridLayout(0, 2, 5, 5));
                orderDateField.setDateFormatString("yyyy-MM-dd");
                deliveryDateField.setDateFormatString("yyyy-MM-dd");
                empIDField = new JComboBox<>(empIDArray);

                contentPane.add(new JLabel("Employee ID:"));
                contentPane.add(empIDField);
                contentPane.add(new JLabel("Stock ID:"));
                contentPane.add(stockIDField);
                contentPane.add(new JLabel("Order Date:"));
                contentPane.add(orderDateField);
                contentPane.add(new JLabel("Total Cost:"));
                contentPane.add(totalCostField);
                contentPane.add(new JLabel("Payment Status:"));
                paymentStatusfield = new JComboBox<>(paymentStat);
                contentPane.add(paymentStatusfield);
                contentPane.add(new JLabel("DeliveryDate"));
                contentPane.add(deliveryDateField);
                Themes.applyButtonTheme(amend);
                contentPane.add(amend);
                amendOrderFrame.pack();
                amendOrderFrame.setLocationRelativeTo(null);
                amendOrderFrame.setVisible(true);


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
                            stockIDField.setSelectedItem(Stock_ID);
                            totalCostField.setText(String.valueOf(TotalCost));
                            paymentStatusfield.setSelectedItem(PaymentStat);

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
                    amend.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            String OrderDateText = orderDateField.getDateFormatString();
                            String TotalCostText = totalCostField.getText();
                            String DeliveryDate = deliveryDateField.getDateFormatString();

                            if(OrderDateText.isEmpty()||TotalCostText.isEmpty()||DeliveryDate.isEmpty()){
                                JOptionPane.showMessageDialog(null,"Please fill in all the fields","Error",JOptionPane.ERROR_MESSAGE);
                                return;//stops further execution if condition not met
                            }

                            if (!NumberOnlyTextField.isValidPrice(TotalCostText)) {
                                JOptionPane.showMessageDialog(null, "Unit price and sale price must contain only numbers and periods", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Stop further execution if condition not met
                            }

                            try {
                                Object selectedEmpId = empIDField.getSelectedItem();
                                Integer Emp_ID = (Integer) selectedEmpId;
                                String selectedStockID = (String) stockIDField.getSelectedItem();
                                String[] parts = selectedStockID.split(" - ");
                                int Stock_ID = Integer.parseInt(parts[0]);
                                BigDecimal TotalCost = new BigDecimal(totalCostField.getText());
                                Object payment = paymentStatusfield.getSelectedItem();
                                String PaymentStat = (String) payment;
                                java.sql.Date orderDate = new java.sql.Date(orderDateField.getDate().getTime());
                                java.sql.Date deliveryDate = new java.sql.Date(deliveryDateField.getDate().getTime());

                                int dialogBox = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure about the Details Entered?","Confirmation",dialogBox);
                                if (dialogResult == JOptionPane.YES_OPTION){
                                    amendCRUD crud = new amendCRUD();
                                    crud.amendIntoOrders((int) primarykey, Emp_ID, Stock_ID, orderDate, TotalCost, PaymentStat, deliveryDate);
                                    JOptionPane.showMessageDialog(null,"Entry Details have been updated");
                                    amendOrderFrame.dispose();
                                }

                            }catch (Exception exception){
                                exception.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error occurred while updating orders", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            table.fetchData();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null,"Please select a entry to amend");

                }


            }
        });
        // Add the button panel to the bottom of the panels.Orders panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the panels.Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));

    }
}
