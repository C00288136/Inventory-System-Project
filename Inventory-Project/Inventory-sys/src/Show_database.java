import java.sql.*;

public class Show_database {
    public static void main(String [] args) {
// database URL
        Connection connection = DatabaseConnector.connect();

        ResultSet resultSet= null;
        PreparedStatement pstat = null;
        try {
            // establish connection to database
// create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("Select * FROM employees;");
            resultSet = pstat.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numuberofColumns = metaData.getColumnCount();
            System.out.println("Employee Table");
            for (int i=1; i<numuberofColumns;i++)
                System.out.print(metaData.getColumnName(i) + "\t" );
            System.out.println();


            while (resultSet.next()){
                for (int i=1; i<numuberofColumns;i++)
                    System.out.print("|" + resultSet.getObject(i) + "\t\t" );
                System.out.println();
            }

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


