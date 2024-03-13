import javax.swing.*;
import java.awt.*;

public class Orders extends JPanel {

    JPanel orderTable = new JPanel();

    JTableExample table = new JTableExample("Orders");

    int tablewidth = 700;
    int tableheight = 400;
    public Orders(){
        setPreferredSize(new Dimension(tablewidth,tableheight));
        add(table);
        setVisible(true);
    }
}
