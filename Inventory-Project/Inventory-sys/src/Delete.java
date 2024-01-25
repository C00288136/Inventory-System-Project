import java . sql .Connection;
import java . sql .DriverManager;
import java . sql .PreparedStatement;
import java . sql .SQLException;

public class Delete {
    public static void main(String [] args) {
// database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/Inventory";
        Connection connection = null ;
        PreparedStatement pstat = null;
        int Employee_ID = 100;

        int i=0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "" );
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
                connection. close () ;
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class


