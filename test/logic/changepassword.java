package logic;
import dbCon.DatabaseConnector;
import loginInterface.loginLogic;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import panels.settings;

import javax.swing.*;


public class changepassword {

    Connection connection;
    DatabaseConnector databaseConnector;



    public void updatePassword(settings settings){
        char[] oldPassword = settings.getOldPassword();
        char[] newPassword = settings.getNewPassword();
        char[] confirmPass = settings.getConfirmPassword();

        if(!Arrays.equals(newPassword, confirmPass)){
            JOptionPane.showMessageDialog(null,"New password doesnt mathc the confirm");
            return;
        }
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
                JOptionPane.showMessageDialog(null,"Password has successfuly changed");
            }
            else{
                JOptionPane.showMessageDialog(null,"Failed to update password");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}
