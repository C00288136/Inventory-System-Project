package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.changepassword;

public class settings extends JPanel {


    private JPasswordField oldPassword = new JPasswordField();
    private JPasswordField newPassword = new JPasswordField();
    private JPasswordField confirmPassword = new JPasswordField();
    private JLabel old = new JLabel("Old Password:");
    private JLabel newLabel = new JLabel("New Password:");
    private JLabel confirm = new JLabel("Confirm password:");

    private JButton confirmPass = new JButton("Confirm Passsword:");

    public settings(){

        setLayout(new GridLayout(1,2));

        JPanel leftPanel = new JPanel();
        add(leftPanel);

//        using gridbag for better component flexibility
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20,20,20,20);

//        old password components
        gbc.gridx = 0;//column 0
        gbc.gridy = 0;
        rightPanel.add(old,gbc);
        gbc.gridx = 1;//added to column 1
        oldPassword.setPreferredSize(new Dimension(200,20));
        rightPanel.add(oldPassword,gbc);

//        new password components
        gbc.gridx = 0;//column 0
        gbc.gridy = 1;
        rightPanel.add(newLabel,gbc);
        gbc.gridx = 1;//added to column 1
        newPassword.setPreferredSize(new Dimension(200,20));
        rightPanel.add(newPassword,gbc);

//        confirm componenets
        gbc.gridx = 0;//column 0
        gbc.gridy = 2;
        rightPanel.add(confirm,gbc);
        gbc.gridx = 1;//added to column 1
        confirmPassword.setPreferredSize(new Dimension(200,20));
        rightPanel.add(confirmPassword,gbc);

        gbc.gridx = 0;//column 0
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(confirmPass,gbc);



        add(rightPanel);

        confirmPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changepassword passwordChanger = new changepassword();

//                calls the update password class with this panel as the argument
                passwordChanger.updatePassword(settings.this);
            }
        });
        setVisible(true);
    }

    public char[] getOldPassword() {
        return oldPassword.getPassword();
    }

    public char[] getNewPassword() {
        return newPassword.getPassword();
    }

    public char[] getConfirmPassword() {
        return confirmPassword.getPassword();
    }
}
