import java.sql.*;

public class JDBCdemo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/JDBC";
        String username = "root";
        String password = "00000000";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from student");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+ " "+ resultSet.getString(2)+" "+ resultSet.getInt(3));
            }

            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
