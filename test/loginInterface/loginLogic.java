package loginInterface;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class loginLogic {


    public static boolean authenticateUser(String Username, String Password){
        String dbPassword = null;

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","");
            PreparedStatement statement = connection.prepareStatement("SELECT Password FROM Employees WHERE Username = ?")){
        statement.setString(1,Username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                dbPassword = resultSet.getString("Password");
                System.out.println(dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dbPassword != null && dbPassword.equals(Password)){
            System.out.println("Passwords match");
            return true;
        }

        else{
            System.out.println("No match");
            return false;
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


}
