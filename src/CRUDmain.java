import java.sql.Connection;
import java.util.Scanner;

public class CRUDmain {

    public static void main(String[] args) {
        Scanner Userin = new Scanner(System.in);
        insertIntoDbCRUD insert =  new insertIntoDbCRUD();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        //i can use the base connector but then need the connection class to actual forward the info to the table
        Connection connection = databaseConnector.connect();
        System.out.println("Choose which data base you want to view/insert/delete");
        DisplayTablesNames.printTableNames();

        int choice = Userin.nextInt();

        while(choice != 0){
            switch (choice){

                case 1:
                    System.out.println("Do you want do insert\ndelete\nview table");
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
                    }
                case 2:
                case 3:
                case 4:
            }
        }





        DatabaseConnector.disconnect();

    }
}
