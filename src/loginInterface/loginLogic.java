package loginInterface;

import dbCon.DatabaseConnector;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class loginLogic {

    private static String currentUsername = null;

    public static String getCurrentUsername(){
        return currentUsername;
    }
    public static String authenticateUser(String Username, String Password){
        String dbPassword = null;
        String username = null;


        try(Connection connection = DatabaseConnector.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT Username, Password FROM Employees WHERE Username = ?")){
        statement.setString(1,Username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                username = resultSet.getString("Username");
                dbPassword = resultSet.getString("Password");

                System.out.println("DB pass :"+dbPassword);
                System.out.println("Entered pass: " + Password);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dbPassword != null && dbPassword.equals(Password)){
            System.out.println("Passwords match");
            currentUsername = Username;
            return username;
        }

        else{
            System.out.println("No match");
            return null;
        }

    }

    public static String hashPassword(String password) {
        try{
//            use messagedigest for built-in encryption
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());

            byte[] bytes = md.digest();
//use a string builder to convert the byte code into a workable hex value
            StringBuilder sb = new StringBuilder();
            for (byte thisbyte : bytes){
                sb.append(String.format("%02x",thisbyte));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }
    }

    public static void addEnterKeyListener(JTextField text, JButton button){
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    button.doClick();
                }
            }
        });
    }



}
