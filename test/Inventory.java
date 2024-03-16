import javax.swing.*;
import java.awt.*;

public class Inventory extends JPanel {

    JTableExample dataTable = new JTableExample("inventory");


    public Inventory(){
        int tablewidth = 700;
        int tableheight = 400;

        setPreferredSize(new Dimension(tablewidth,tableheight));
        add(dataTable);
        setVisible(true);
    }
}
