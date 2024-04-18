package logic;
import dbCon.DatabaseConnector;
import loginInterface.loginLogic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import panels.settings;
import javax.swing.*;

/**
 * Class used for querying the database to change the users password
 */
public class changepassword {
    /**
     *
     * @param settings - Jpanel which the password fields entered are retrieved from
     */
    public void updatePassword(settings settings){
        char[] oldPassword = settings.getOldPassword();
        char[] newPassword = settings.getNewPassword();
        char[] confirmPass = settings.getConfirmPassword();
//      validation to make sure the new password matches the confirmation
        if(!Arrays.equals(newPassword, confirmPass)){
            JOptionPane.showMessageDialog(null,"New password doesn't match the confirm");
            return;
        }
//        check if the new password is not the same as the old
        if (Arrays.equals(oldPassword,newPassword)|| Arrays.equals(oldPassword,confirmPass) ){
            JOptionPane.showMessageDialog(null,"New password cannot be the same as the old Password");
            return;
        }

//        creates string object straight away while hashing the password
        String hashedPassword = loginLogic.hashPassword(new String(newPassword));

//        perform password Update
        try(Connection connection = DatabaseConnector.connect();
            PreparedStatement pstat = connection.prepareStatement("Update Employees set Password = ?  Where Username = ?")) {
            pstat.setString(1,hashedPassword);
            String username = loginLogic.getCurrentUsername();
            pstat.setString(2,username);

            int rowAffected = pstat.executeUpdate();
            if (rowAffected > 0){
                JOptionPane.showMessageDialog(null,"Password has successfully changed");
            }
            else{
                JOptionPane.showMessageDialog(null,"Failed to update password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}
