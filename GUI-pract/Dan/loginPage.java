// the following is a sample login page that utilises java swing and java event
// 

package Dan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class loginPage implements ActionListener {

    //initialising buttons, frames, and fields used
    JFrame frame = new JFrame();
    JButton loginB = new JButton("Login");
    JButton resetB = new JButton("Reset");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password: ");

    //constructor will set everything up
    public loginPage() {
        usernameLabel.setBounds(50, 100, 75, 25);
        passwordLabel.setBounds(50, 150, 75, 25);

        usernameField.setBounds(125,100,200,25);
        passwordField.setBounds(125,150,200,25);

        loginB.setBounds(125,200,100,25);
        loginB.addActionListener(this);

        resetB.setBounds(225,200,100,25);
        resetB.addActionListener(this);

        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginB);
        frame.add(resetB);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==resetB) {
            usernameField.setText("");
            passwordField.setText("");
        }
        if(e.getSource()==loginB) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            if (validateLogin(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password");
            }
            
        }
    }

    public boolean validateLogin(String username, String password)
    {
        Connection connection = DatabaseConnector2.connect(username, password);
        if (connection != null) {
            return true;
        } else {
            return false;
        }
    }
}
