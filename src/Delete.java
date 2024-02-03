import javax.xml.crypto.Data;
import java . sql .Connection;
import java . sql .DriverManager;
import java . sql .PreparedStatement;
import java . sql .SQLException;

public class Delete {
    public static void main(String [] args) {
        // establish connection to database
        Connection connection = DatabaseConnector.connect();
        PreparedStatement pstat = null;
        int Employee_ID = 100;

        int i=0;
        try {
// create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("DELETE From Employees Where Emp_ID=?");
            pstat.setInt(1,Employee_ID);

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
                DatabaseConnector.disconnect();
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class


