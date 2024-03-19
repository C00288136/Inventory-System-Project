package loginInterface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginInterface extends JPanel {

    JLabel userLabel = new JLabel("Username");
    public JTextField userText = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    public JPasswordField passwordText =  new JPasswordField();
    public JButton loginButton = new JButton("Login");
    JLabel errorMessageLabel = new JLabel("");

    public loginInterface(){
        userLabel.setBounds(10,20,80,25);
        add(userLabel);

        userText.setBounds(100,20,165,25);
        add(userText);

        passwordLabel.setBounds(10,50,80,25);
        add(passwordLabel);

        passwordText.setBounds(100,50,165,25);
        add(passwordText);

        loginButton.setBounds(10,80,80,25);

        add(loginButton);
        setLayout(null);
        setVisible(true);

    }
}

