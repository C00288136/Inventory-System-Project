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
    private JLabel old = new JLabel("Old Password");
    private JLabel newLabel = new JLabel("New Password");
    private JLabel confirm = new JLabel("Confirm password");

    private JButton confirmPass = new JButton("Confirm Passsword");

    public settings(){

        setLayout(new FlowLayout());

        oldPassword.setPreferredSize(new Dimension(200,20));
        newPassword.setPreferredSize(new Dimension(200,20));
        confirmPassword.setPreferredSize(new Dimension(200,20));

        add(old);
        add(oldPassword);
        add(newLabel);
        add(newPassword);
        add(confirm);
        add(confirmPassword);
        add(confirmPass);

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
