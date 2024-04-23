package loginInterface;


import Design.Themes;

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

    public loginInterface() {
        setLayout(new GridLayout(1,2));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Using null layout for precise positioning

        Font font = Themes.getTextfont();
        userLabel.setFont(font);
        userText.setFont(font);
        passwordLabel.setFont(font);
        loginButton.setFont(font);


        JPanel rightPanel = new JPanel();
//        box layout used to centre the image vertically
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        int panelWidth = 650;
        int panelHeight = 425;
        rightPanel.setBackground(Color.decode("#e2f3fa"));
//        method for scaling the image to correct size
        ImageIcon logoImage = new ImageIcon("assets/Designer.jpeg");
        Image scaledImage = logoImage.getImage().getScaledInstance(panelWidth,panelHeight,Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        
//        creates an invisible component above the logolabel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(logoLabel);
//        creates an invisible component below the logo label essentially centering it in the frame
        rightPanel.add(Box.createVerticalGlue());
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


        loginButton.setFont(Themes.getButtonfont());
        loginButton.setBackground(Color.decode("#e2f3fa"));
        loginPanel.add(loginButton,gbc);


        add(loginPanel); // Place loginPanel on the left



        setVisible(true);
    }


}