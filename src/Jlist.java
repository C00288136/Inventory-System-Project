// This class will be used for dynamically fetching and displaying existing records


import javax.swing.*;
import java.sql.Connection;



public class Jlist extends JPanel {
    Connection connection;
    String tableName;

    public Jlist(String tableName)
    {
        this.tableName = tableName;
//        JList
    }

}
