package loginInterface;


import javax.swing.*;
import java.awt.*;

/**
 * JPanel class which creates the GUI interface for the user to log in
 */
public class loginInterface extends JPanel {

    JLabel userLabel = new JLabel("Username");
    public JTextField userText = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    public JPasswordField passwordText =  new JPasswordField();
    public JButton loginButton = new JButton("Login");

    int panelWidth = getWidth()/2;

    public loginInterface() {
        setLayout(new GridLayout(1,2));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Using null layout for precise positioning

        Font font = new Font(Font.SANS_SERIF,Font.PLAIN,16);
        userLabel.setFont(font);
        userText.setFont(font);
        passwordLabel.setFont(font);
        loginButton.setFont(font);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY); // Set the background color as needed
        add(rightPanel); // Place it on the right


        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;

        loginPanel.add(userLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        userText.setPreferredSize(new Dimension(200,25));
        loginPanel.add(userText,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        loginPanel.add(passwordLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        passwordText.setPreferredSize(new Dimension(200,25));
        loginPanel.add(passwordText,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        loginPanel.add(loginButton,gbc);

        add(loginPanel); // Place loginPanel on the left

        setVisible(true);
    }


}