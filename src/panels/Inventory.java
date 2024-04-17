package panels;

import logic.DeleteCRUD;
import logic.InsertIntoDbCRUD;
import logic.amendCRUD;
import logic.table;
import dbCon.DatabaseConnector;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Inventory extends JPanel {

    table dataTable = new table("Stock_items");
    JButton addItem = new JButton("Add Item(s)");
    JButton deleteItem = new JButton("Delete Item");
    JButton amendItem = new JButton("Amend Item");
    JButton insert = new JButton("Insert Item");
    JButton amend = new JButton("Amend Details");
    JTextField ItemName = new JTextField();
    JTextField quantity = new JTextField();
    JTextField unitP = new JTextField();
    JTextField SaleP = new JTextField();
    JTextField SupplierID = new JTextField();
    JComboBox<Integer> Aisle;
    InsertIntoDbCRUD crudIn = new InsertIntoDbCRUD();
    DeleteCRUD deleteCRUD = new DeleteCRUD();
    amendCRUD amendCRUD = new amendCRUD();

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

                        crudIn.insertIntoStockItems(Name,quant,unit,sale,supp,aisle);
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

        amendItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                JFrame AmendItemsFrame = new JFrame("Amend Item");
                Aisle = new JComboBox<>(aisles);

                AmendItemsFrame.setPreferredSize(new Dimension(200,400));

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
                panel.add(amend);
                AmendItemsFrame.setLocationRelativeTo(getParent());
                AmendItemsFrame.add(panel);
                AmendItemsFrame.pack();
                AmendItemsFrame.setVisible(true);

                
                Object primarykey = dataTable.getSelectedPrimaryKey();
                if (primarykey != null){
                       int id = (int) primarykey;

                    Connection con = DatabaseConnector.connect();
                    try{
                       PreparedStatement pstat = con.prepareStatement("SELECT * FROM Stock_Items where product_ID = ?");
                       pstat.setInt(1,id);

                        ResultSet rs = pstat.executeQuery();

                        while (rs.next()){
                            String name = rs.getString("Name");
                            int qty = rs.getInt("quantity_in_stock");
                            BigDecimal unitPrice = rs.getBigDecimal("unit_price");
                            BigDecimal salePrice = rs.getBigDecimal("sale_price");
                            int supplierID = rs.getInt("supplier_ID");
                            int aisle = rs.getInt("Aisle_num");

                            ItemName.setText(name);
                            quantity.setText(String.valueOf(qty));
                            unitP.setText(unitPrice.toString());
                            SaleP.setText(salePrice.toString());
                            SupplierID.setText(String.valueOf(supplierID));
                            Aisle.setSelectedItem(aisle);   
                        }
                        rs.close();   
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                    }
                else {
                    JOptionPane.showMessageDialog(getParent(),"Please select a entry to amend");
                }
                    amend.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String nameamended = ItemName.getText();
                            int qtyamended = Integer.parseInt(quantity.getText());
                            BigDecimal unitPriceamended = new BigDecimal(unitP.getText());
                            BigDecimal salePriceamended = new BigDecimal(SaleP.getText());
                            int supplierIDamended = Integer.parseInt(SupplierID.getText());
                            Object selectedAisle = Aisle.getSelectedItem();
                            Integer aisleamended = (Integer) selectedAisle;

                            int dialogBox = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(getParent(),"Are you sure about the Details Entered?","Confirmation",dialogBox);
                            if (dialogResult == JOptionPane.YES_OPTION){

                            amendCRUD.amendStockItems((int) primarykey,nameamended,qtyamended,unitPriceamended,salePriceamended,supplierIDamended,aisleamended);
                                JOptionPane.showMessageDialog(getParent(),"Entry Details have been updated");
                            }
                            dataTable.fetchData();
                        }
                    });

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
