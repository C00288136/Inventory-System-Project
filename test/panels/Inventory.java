package panels;

import logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class Inventory extends JPanel {

    table dataTable = new table("Stock_items");
    JButton addItem = new JButton("Add Item(s)");
    JButton deleteItem = new JButton("Delete Item");
    JButton amendItem = new JButton("Amend Item");
    JButton insert = new JButton("Insert Item");

    JTextField ItemName = new JTextField();
    JTextField quantity = new JTextField();
    JTextField unitP = new JTextField();
    JTextField SaleP = new JTextField();
    JTextField SupplierID = new JTextField();
    JComboBox<Integer> Aisle;
    InsertIntoDbCRUD crud = new InsertIntoDbCRUD();
    DeleteCRUD deleteCRUD = new DeleteCRUD();


    Integer[] aisles = {1,2,3,4,5,6};




    public Inventory(){
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding
        addItem.setPreferredSize(new Dimension(120, 30));
        deleteItem.setPreferredSize(new Dimension(120, 30));
        amendItem.setPreferredSize(new Dimension(120, 30));


        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addItemFrame = new JFrame("Add Item");
                Aisle = new JComboBox<>(aisles);
                int width = 100;
                int height = 300;
                addItemFrame.setPreferredSize(new Dimension(200,400));

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(7,2,5,5));
                panel.add(new JLabel("Name"));
                panel.add(ItemName);
                panel.add(new JLabel("Quantity"));
                panel.add(quantity);
                panel.add(new JLabel("Unit Price"));
                panel.add(unitP);
                panel.add(new JLabel("Sale Price"));
                panel.add(SaleP);
                panel.add(new JLabel("Supplier ID"));
                panel.add(SupplierID);
                panel.add(new JLabel("Aisle Number"));
                panel.add(Aisle);
                panel.add(insert);
                addItemFrame.setLocationRelativeTo(null);
                addItemFrame.add(panel);
                addItemFrame.pack();
                addItemFrame.setVisible(true);

                insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String Name = ItemName.getText();
                        int quant = Integer.parseInt(quantity.getText());
                        BigDecimal unit = new BigDecimal(unitP.getText());
                        BigDecimal sale = new BigDecimal(SaleP.getText());

                        int supp = Integer.parseInt(SupplierID.getText());
                        Object selectedAisle = Aisle.getSelectedItem();
                        Integer aisle = (Integer) selectedAisle;


                        try {

                        crud.insertIntoStockItems(Name,quant,unit,sale,supp,aisle);
                        JOptionPane.showMessageDialog(null,"Entry has been added successfully");

                        addItemFrame.dispose();
                        }
                        catch (Exception exception){
                            exception.printStackTrace();

                            JOptionPane.showMessageDialog(null,"Error occured while adding the Item","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        dataTable.fetchData();
                    }
                });
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object primarykey = dataTable.getSelectedPrimaryKey();
                if (primarykey != null) {
                    int row = (int) primarykey;
                    int dialogBox = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this record", "Warning", dialogBox);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteCRUD.deleteFromTable("Stock_items",row);
                        System.out.println(row + " Deleted!");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null,"Please select a row to delete.");
                }
                dataTable.fetchData();
            }
        });

        buttonPanel.add(addItem);
        buttonPanel.add(deleteItem);
        buttonPanel.add(amendItem);
        add(dataTable,BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
