package panels;

import javax.swing.*;
import java.awt.*;
import logic.changepassword;

/**
 * settings panel where to user can change their password in the current version
 */
public class settings extends JPanel {
    private final JPasswordField oldPassword = new JPasswordField();
    private final JPasswordField newPassword = new JPasswordField();
    private final JPasswordField confirmPassword = new JPasswordField();

    public settings(){

        setLayout(new GridLayout(1,2));

        JPanel leftPanel = new JPanel();
        add(leftPanel);

//        using grid bag for better component flexibility
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20,20,20,20);

//        old password components
        gbc.gridx = 0;//column 0
        gbc.gridy = 0;
        JLabel old = new JLabel("Old Password:");
        rightPanel.add(old,gbc);
        gbc.gridx = 1;//added to column 1
        oldPassword.setPreferredSize(new Dimension(200,20));
        rightPanel.add(oldPassword,gbc);

//        new password components
        gbc.gridx = 0;//column 0
        gbc.gridy = 1;
        JLabel newLabel = new JLabel("New Password:");
        rightPanel.add(newLabel,gbc);
        gbc.gridx = 1;//added to column 1
        newPassword.setPreferredSize(new Dimension(200,20));
        rightPanel.add(newPassword,gbc);

//        confirm components
        gbc.gridx = 0;//column 0
        gbc.gridy = 2;
        JLabel confirm = new JLabel("Confirm password:");
        rightPanel.add(confirm,gbc);
        gbc.gridx = 1;//added to column 1
        confirmPassword.setPreferredSize(new Dimension(200,20));
        rightPanel.add(confirmPassword,gbc);

//        confirm button components
        gbc.gridx = 0;//column 0
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton confirmPass = new JButton("Confirm Password:");
        rightPanel.add(confirmPass,gbc);
        add(rightPanel);

        confirmPass.addActionListener(e -> {
            changepassword passwordChanger = new changepassword();
//                calls the update password class with this panel as the argument
            passwordChanger.updatePassword(settings.this);
            oldPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
        });
        setVisible(true);
    }

    /**
     *
     * @return - old password
     */
    public char[] getOldPassword() {
        return oldPassword.getPassword();
    }

    /**
     *
     * @return - new password
     */
    public char[] getNewPassword() {
        return newPassword.getPassword();
    }

    /**
     *
     * @return - new password typed again to confirm
     */
    public char[] getConfirmPassword() {
        return confirmPassword.getPassword();
    }
}
