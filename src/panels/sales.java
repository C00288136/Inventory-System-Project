package panels;

import logic.table;
import logic.DeleteCRUD;
import logic.InsertIntoDbCRUD;
import logic.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.math.BigDecimal;

public class sales extends JPanel {

    table table = new table("Sales");

    JButton addSale = new JButton("Add Sale");
    JButton deleteSale = new JButton("Delete Sale");
    JButton ammendSale = new JButton("Amend Sale");
    JButton insert = new JButton("Add Sale");
    JButton delete = new JButton("Delete");
    JTextField saleIDField = new JTextField();
    JTextField empIDField = new JTextField();
    JTextField stockIDField = new JTextField();
    JTextField SaleDateField = new JTextField();
    JTextField TotalPriceField = new JTextField();
    JTextField QuantityField = new JTextField();
    JTextField PaymentMethodField = new JTextField();
    InsertIntoDbCRUD crud = new InsertIntoDbCRUD();
    DeleteCRUD deleteCrud = new DeleteCRUD();

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
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        addSale.setFont(buttonFont);
        deleteSale.setFont(buttonFont);
        ammendSale.setFont(buttonFont);


        buttonPanel.add(addSale);
        buttonPanel.add(deleteSale);
        buttonPanel.add(ammendSale);

        addSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addSaleFrame = new JFrame();
        
                // Add components to the frame
                Container contentPane = addSaleFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5));
                contentPane.add(new JLabel("Employee ID:"));
                contentPane.add(empIDField);
                contentPane.add(new JLabel("Stock ID:"));
                contentPane.add(stockIDField);
                contentPane.add(new JLabel("Sale Date:"));
                contentPane.add(SaleDateField);
                contentPane.add(new JLabel("Total Price:"));
                contentPane.add(TotalPriceField);
                contentPane.add(new JLabel("Quantity:"));
                contentPane.add(QuantityField);
                contentPane.add(new JLabel("Payment Method:"));
                contentPane.add(PaymentMethodField);
                contentPane.add(insert);

                insert.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int empID = Integer.parseInt(empIDField.getText());
                        int stockID = Integer.parseInt(stockIDField.getText());
                        Date saleDate = Date.valueOf(SaleDateField.getText());
                        BigDecimal totalPrice = new BigDecimal(TotalPriceField.getText());
                        int quantity = Integer.parseInt(QuantityField.getText());
                        String paymentMethod = PaymentMethodField.getText();

                        crud.insertIntoSales(empID, stockID, saleDate, totalPrice, quantity, paymentMethod);
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
            public void actionPerformed(ActionEvent e) {
                JFrame deleteSaleFrame = new JFrame();

                Container contentPane = deleteSaleFrame.getContentPane();
                contentPane.setLayout(new GridLayout(7, 2, 5, 5));

                contentPane.add(new JLabel("Sale ID:"));
                contentPane.add(saleIDField);
                contentPane.add(delete);

                delete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int saleID = Integer.parseInt(saleIDField.getText());

                        deleteCrud.deleteFromTable("Sales", saleID);
                        table.fetchData();
                    }
                });

                deleteSaleFrame.pack();
                deleteSaleFrame.setSize(400,300);
                deleteSaleFrame.setLocationRelativeTo(null);
                deleteSaleFrame.setVisible(true);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the panels.Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));
        setVisible(true);


    }

}
