package panels;

import Design.Themes;
import com.toedter.calendar.JDateChooser;
import logic.DeleteCRUD;
import logic.InsertIntoDbCRUD;
import logic.homeLogic;
import logic.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class sales extends JPanel {

    table table = new table("Sales");

    JButton addSale = new JButton("Add Sale");
    JButton deleteSale = new JButton("Delete Sale");
    JButton ammendSale = new JButton("Amend Sale");
    JButton insert = new JButton("Add Sale");
    JComboBox<Integer> empIDField;
    List<Integer> empIDs = homeLogic.fetchEmpID();
    Integer[] empIds = empIDs.toArray(new Integer[0]);
    JComboBox<String> stockIDField;
    List<String> stockItems = homeLogic.fetchItemsFromStock();
    String[] stockItemsArray = stockItems.toArray(new String[0]);
    JDateChooser saleDateField  = new JDateChooser();
    JTextField TotalPriceField = new JTextField();
    JTextField QuantityField = new JTextField();
    JComboBox<String> paymentField = new JComboBox<>();
    String[] paymentMethods = {"cash","credit","debit"};
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
                        JOptionPane.showMessageDialog(null,"Entry added to the Sales table");
                        addSaleFrame.dispose();
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


        add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size of the panels.Orders panel
        setPreferredSize(new Dimension(tablewidth, tableheight + buttonPanelHeight));
        setVisible(true);


    }



}
