package panels;

import Design.Themes;
import Validation.NumberOnlyTextField;
import com.toedter.calendar.JDateChooser;
import dbCon.DatabaseConnector;
import logic.DeleteCRUD;
import logic.InsertIntoDbCRUD;
import logic.homeLogic;
import logic.table;
import logic.amendCRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
 * class for sales panel
 */

public class sales extends JPanel {

    table table = new table("Sales");

    JButton addSale = new JButton("Add Sale");
    JButton deleteSale = new JButton("Delete Sale");
    JButton ammendSale = new JButton("Amend Sale");
    JButton insert = new JButton("Add Sale");
    JButton amend = new JButton("Amend");
    JComboBox<Integer> empIDField;
    List<Integer> empIDs = homeLogic.fetchEmpID();
    Integer[] empIds = empIDs.toArray(new Integer[0]);
    JComboBox<String> stockIDField;
    List<String> stockItems = homeLogic.fetchItemsFromStock();
    String[] stockItemsArray = stockItems.toArray(new String[0]);
    JDateChooser saleDateField  = new JDateChooser();
    JTextField TotalPriceField = new JTextField();
    JTextField QuantityField = new NumberOnlyTextField(5);
    JComboBox<String> paymentField = new JComboBox<>();
    String[] paymentMethods = {"cash","credit","debit"};
    InsertIntoDbCRUD crud = new InsertIntoDbCRUD();
    DeleteCRUD deleteCrud = new DeleteCRUD();
    amendCRUD amendCRUD = new amendCRUD();

    int tablewidth = 800;
    int tableheight = 400;
    int buttonPanelHeight = 50;


    public sales(){
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        addSale.setPreferredSize(new Dimension(120, 30));
        deleteSale.setPreferredSize(new Dimension(120, 30));
        ammendSale.setPreferredSize(new Dimension(120, 30));
        Themes.applyButtonTheme(addSale);
        Themes.applyButtonTheme(deleteSale);
        Themes.applyButtonTheme(ammendSale);


        buttonPanel.add(addSale);
        buttonPanel.add(deleteSale);
        buttonPanel.add(ammendSale);

        addSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addSaleFrame = new JFrame();
                saleDateField.setDateFormatString("yyyy-MM-dd");
                empIDField = new JComboBox<>(empIds);
                stockIDField = new JComboBox<>(stockItemsArray);
                System.out.println(stockIDField);

                // Add components to the frame
                Container contentPane = addSaleFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5));
                contentPane.add(new JLabel("Employee ID:"));
                contentPane.add(empIDField);
                contentPane.add(new JLabel("Stock ID:"));
                contentPane.add(stockIDField);
                contentPane.add(new JLabel("Sale Date:"));
                contentPane.add(saleDateField);
                contentPane.add(new JLabel("Total Price:"));
                contentPane.add(TotalPriceField);
                contentPane.add(new JLabel("Quantity:"));
                contentPane.add(QuantityField);
                contentPane.add(new JLabel("Payment Method:"));
                paymentField = new JComboBox<>(paymentMethods);
                contentPane.add(paymentField);
                contentPane.add(insert);

                insert.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String saleDateText = saleDateField.getDateFormatString();
                        String TotalPriceText = TotalPriceField.getText();
                        String QuantityText = QuantityField.getText();



                        if(saleDateText.isEmpty()||TotalPriceText.isEmpty()||QuantityText.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Please fill in all the fields","Error",JOptionPane.ERROR_MESSAGE);
                            return;//stops further execution if condition not met
                        }

                        if (!NumberOnlyTextField.isValidPrice(TotalPriceText)) {
                            JOptionPane.showMessageDialog(null, "Unit price and sale price must contain only numbers and periods", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Stop further execution if condition not met
                        }
                        try {


                            Object selectedEmpId = empIDField.getSelectedItem();
                            int empID = (Integer) selectedEmpId;
                            String selectedStockID = (String) stockIDField.getSelectedItem();
                            String[] parts = selectedStockID.split(" - ");
                            int stockID = Integer.parseInt(parts[0]);
                            java.sql.Date saleDate = new java.sql.Date(saleDateField.getDate().getTime());
                            BigDecimal totalPrice = new BigDecimal(TotalPriceField.getText());
                            int quantity = Integer.parseInt(QuantityField.getText());
                            Object paymentMethod = paymentField.getSelectedItem();
                            String payment = (String) paymentMethod;

                            crud.insertIntoSales(empID, stockID, saleDate, totalPrice, quantity, payment);
                            JOptionPane.showMessageDialog(null, "Entry added to the Sales table");
                            addSaleFrame.dispose();
                        }
                        catch (Exception exception){
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(null,"Error occurred while adding the Item","Error",JOptionPane.ERROR_MESSAGE);

                        }
                        table.fetchData();
                        
                    }
                });
        
                // Pack and set size for the frame
                addSaleFrame.pack();
                addSaleFrame.setSize(400, 300); // Set an appropriate size
                addSaleFrame.setLocationRelativeTo(null);
                addSaleFrame.setVisible(true);
            }
        });

        deleteSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object primarykey = table.getSelectedPrimaryKey();
                if (primarykey != null) {
                    int row = (int) primarykey;
                    int dialogBox = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this record", "Warning", dialogBox);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteCrud.deleteFromTable("Sales",row);
                        System.out.println(row + " Deleted!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select a row to delete.");
                }
                table.fetchData();
            }

        });

        ammendSale.addActionListener(e -> {
            JFrame AmendSaleFrame = new JFrame();
            saleDateField.setDateFormatString("yyyy-MM-dd");
            empIDField = new JComboBox<>(empIds);
            stockIDField = new JComboBox<>(stockItemsArray);
            System.out.println(stockIDField);

            // Add components to the frame
            Container contentPane = AmendSaleFrame.getContentPane();
            contentPane.setLayout(new GridLayout(7, 2, 5, 5));
            contentPane.add(new JLabel("Employee ID:"));
            contentPane.add(empIDField);
            contentPane.add(new JLabel("Stock ID:"));
            contentPane.add(stockIDField);
            contentPane.add(new JLabel("Sale Date:"));
            contentPane.add(saleDateField);
            contentPane.add(new JLabel("Total Price:"));
            contentPane.add(TotalPriceField);
            contentPane.add(new JLabel("Quantity:"));
            contentPane.add(QuantityField);
            contentPane.add(new JLabel("Payment Method:"));
            paymentField = new JComboBox<>(paymentMethods);
            contentPane.add(paymentField);
            Themes.applyButtonTheme(amend);
            contentPane.add(amend);
            AmendSaleFrame.pack();
            AmendSaleFrame.setSize(400, 300); // Set an appropriate size
            AmendSaleFrame.setVisible(true);


            Object primarykey = table.getSelectedPrimaryKey();

            if (primarykey != null) {
                int row = (int) primarykey;
                System.out.println(row);

                Connection con = DatabaseConnector.connect();
                try{
                    PreparedStatement pstat = con.prepareStatement("SELECT * FROM sales where Sale_ID = ?");
                    pstat.setInt(1,row);

                    ResultSet rs = pstat.executeQuery();

                    while (rs.next()){
                        int empID = rs.getInt("Emp_ID");
                        int stockID = rs.getInt("Stock_id");
                        java.sql.Date saleDate = rs.getDate("SaleDate");
                        BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
                        int quantity = rs.getInt("Quantity");
                        String paymentMethod = rs.getString("Payment_Method");

                        // Set values in Amend Sale frame
                        empIDField.setSelectedItem(empID);
//                        BUG: cannot set the stockID as it contains the stockID (number) but the set the JcomboBox it would
//                        need to be able to set the item using (1 - Apple)
                        stockIDField.setSelectedItem(stockID);
                        stockIDField.setEditable(false);
                        saleDateField.setDate(saleDate);
                        TotalPriceField.setText(totalPrice.toString());
                        QuantityField.setText(String.valueOf(quantity));
                        paymentField.setSelectedItem(paymentMethod);
                    }
                    rs.close();
                    con.close();
                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Please select a entry to amend");
                return;
            }

            amend.addActionListener(e1 ->{

                String empIDText = String.valueOf(empIDField.getSelectedItem());  // Assuming empIDField returns a String value
                String stockIDText = (String) stockIDField.getSelectedItem();  // Assuming stockIDField returns a String value
                String saleDateText = saleDateField.getDateFormatString();
                String totalPriceText = TotalPriceField.getText();
                String quantityText = QuantityField.getText();

                if (saleDateText.isEmpty()|| totalPriceText.isEmpty()||quantityText.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop further execution if condition not met
                }

                if (!NumberOnlyTextField.isValidPrice(totalPriceText)){
                    JOptionPane.showMessageDialog(null, "Total Price must contain only numbers and periods", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int empID = Integer.parseInt(empIDText);
                    String[] parts = stockIDText.split(" - ");
                    String StockID = parts[0];
                    int stockID = Integer.parseInt(StockID);  // Assuming stockID is an integer
                    java.sql.Date saleDate = new java.sql.Date(saleDateField.getDate().getTime()); // Assuming valid date format
                    BigDecimal totalPrice = new BigDecimal(totalPriceText);
                    int quantity = Integer.parseInt(quantityText);
                    String paymentMethodText = (String) paymentField.getSelectedItem();


                    int dialogBox = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure about the Details Entered?", "Confirmation", dialogBox);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        amendCRUD.amendSale((int) primarykey,empID,stockID,saleDate,totalPrice,quantity,paymentMethodText);
                        JOptionPane.showMessageDialog(null, "Sale Entry has been updated");
                        AmendSaleFrame.dispose();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred while updating sale", "Error", JOptionPane.ERROR_MESSAGE);
                }

                table.fetchData();  // Refresh the table data

            });
        });


        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the panels.Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));
        setVisible(true);


    }



}
