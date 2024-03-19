package loginInterface;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginInterface {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JPanel body = new JPanel();

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        body.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100,20,165,25);
        body.add(userText);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        body.add(passwordLabel);


        JPasswordField passwordText =  new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        body.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10,80,80,25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordChars = passwordText.getPassword();
                String password = new String(passwordChars);

                String username = userText.getText();

                loginLogic login =  new loginLogic();

                String Password = login.hashPassword(password);
                Boolean authenticate = login.authenticateUser(username,Password);
                if (authenticate == true){
                    JOptionPane.showMessageDialog(frame, "Login Successful");
                }
                else {
                    JOptionPane.showMessageDialog(frame,"Invalid username or password");
                }



            }
        });
        body.add(loginButton);



        frame.add(body);
        body.setLayout(null);
        frame.setSize(300,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






        frame.setVisible(true);
    }
}

