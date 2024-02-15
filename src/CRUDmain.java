import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

public class CRUDmain {

    public static void main(String[] args) {
        Scanner Userin = new Scanner(System.in);
        Date currentDate = new Date(System.currentTimeMillis());
        InsertIntoDbCRUD insert =  new InsertIntoDbCRUD();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        //i can use the base connector but then need the connection class to actual forward the info to the table
        Connection connection = databaseConnector.connect();
        System.out.println("Choose which data base you want to view/insert/delete");
        DisplayTablesNames.printTableNames();

        int choice = Userin.nextInt();

        while(choice != 0){
            switch (choice){

                case 1:
                    System.out.println("Do you want to \ninsert\ndelete\nview table");
                    String userChoice = Userin.nextLine().toLowerCase();
                    switch (userChoice){
                        case "insert":
                        System.out.println("Inserting data into Employees table:");
                        System.out.print("Enter Last Name: ");
                        String lastName = Userin.nextLine();

                        System.out.print("Enter First Name: ");
                        String firstName = Userin.nextLine();

                        System.out.print("Enter Age: ");
                        int age = Userin.nextInt();
                        Userin.nextLine(); // Consume the newline character

                        System.out.print("Enter Phone Number: ");
                        long phoneNo = Userin.nextLong();
                        Userin.nextLine(); // Consume the newline character

                        System.out.print("Enter Address: ");
                        String address = Userin.nextLine();

                        insert.insertIntoEmployees(lastName,firstName,age,phoneNo,address);
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Do you want to \ninsert\ndelete\nview table");
                    userChoice = Userin.nextLine().toLowerCase();
                    switch (userChoice) {
                        case "insert":
                            System.out.println("Inserting data into Orders table:");
                            System.out.print("Enter Emp_ID: ");
                            int Emp_Id = Userin.nextInt();

                            System.out.print("Enter Stock_ID: ");
                            int Stock_id = Userin.nextInt();


                            System.out.print("Enter Total Cost: ");
                            BigDecimal Total = Userin.nextBigDecimal();

                            System.out.print("Enter Payment Status (paid, pending) : ");
                            String PayStat = Userin.nextLine().toLowerCase();

                            long delivery_in_2 = 2 * 24 * 60 * 60 * 1000L;
                            long currentTime = System.currentTimeMillis();
                            long Delivery = currentTime + delivery_in_2;

                            Date Delivery_date = new Date(Delivery);

                            insert.insertIntoOrders(Emp_Id, Stock_id, currentDate, Total, PayStat, Delivery_date);
                            break;
                    }
                    break;
                case 3:
                    break;

                case 4:
                    break;

            }
        }





        DatabaseConnector.disconnect();

    }
}

