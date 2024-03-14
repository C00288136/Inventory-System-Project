import javax.swing.*;
import java.awt.*;

public class sales extends JPanel {

    JTableExample dataTable = new JTableExample("Sales");

    int tablewidth = 700;
    int tableheight = 400;
    public sales(){
    setPreferredSize(new Dimension(tablewidth,tableheight));
    add(dataTable);
    setVisible(true);

    }
}
