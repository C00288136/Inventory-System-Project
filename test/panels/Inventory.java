package panels;

import logic.table;

import javax.swing.*;
import java.awt.*;
public class Inventory extends JPanel {

    table dataTable = new table("Stock_items");


    public Inventory(){
        int tablewidth = 700;
        int tableheight = 400;

        setPreferredSize(new Dimension(tablewidth,tableheight));
        add(dataTable);
        setVisible(true);
    }
}
