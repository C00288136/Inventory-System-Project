package Dan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiDriver {

    JFrame frame = new JFrame("Main menu");
    JButton Inventory = new JButton("Inventory");
    JPanel TablePanel = new JPanel();
    JPanel navBar = new JPanel();
    JPanel body = new JPanel();

    public guiDriver()
    {
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(navBar, BorderLayout.WEST);
        frame.add(body, BorderLayout.CENTER);

        navBar.setBackground(Color.black);
        body.setBackground(Color.white);


        navBar.add(Inventory);
        navBar.setLayout(new BoxLayout(navBar, BoxLayout.Y_AXIS));

        body.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        body.add(TablePanel,gbc);

        


        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        guiDriver window = new guiDriver();
    }
    
}
