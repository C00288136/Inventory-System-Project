import java . sql .Connection;
import java . sql .DriverManager;
import java . sql .PreparedStatement;
import java . sql .SQLException;

public class Insert {
    public static void main(String [] args) {
// database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/Inventory";
        Connection connection = null ;
        PreparedStatement pstat = null;
        String firstname = "Mark";
        String lastname = "Power";
        int age = 60;
        long phone_no = 894623784;
        String address = "230 Carmody Square";

        int i=0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "" );
// create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Employees (LastName, FirstName, Age, Phone_No, Address ) VALUES (?,?,?,?,?)");
            pstat . setString (2, firstname );
            pstat . setString (1, lastname);
            pstat.setInt(3,age);
            pstat.setLong(4,phone_no);
            pstat.setString(5,address);
// insert data into table
            i = pstat .executeUpdate();
            System.out. println ( i + " record successfully added to the table .");
        }
        catch(SQLException sqlException){
            sqlException . printStackTrace () ;
        }
        finally {
            try {
                pstat . close () ;
                connection. close () ;
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class


