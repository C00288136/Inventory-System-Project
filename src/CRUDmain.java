import Dan.JTableData;
import Dan.JTableExample;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;


public class CRUDmain {

    public static void main(String[] args) {
        Scanner Userin = new Scanner(System.in);
        Date currentDate = new Date(System.currentTimeMillis());
        InsertIntoDbCRUD insert =  new InsertIntoDbCRUD();
        DeleteCRUD delete = new DeleteCRUD();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        //I can use the base connector but then need the connection class to actual forward the info to the table
        Connection connection = databaseConnector.connect();

        int choice;

        do{
            System.out.println("Choose which data base you want to view/insert/delete");
            DisplayTablesNames.printTableNames();
            choice = Userin.nextInt();
            Userin.nextLine();

            //needed to declare as null to be able to use the objects instead of if statement for update
            JTableExample table = null ;



            switch (choice){

                //Employees switch
                case 1:
                    System.out.println("Do you want to \n1.insert\n2.delete\n3.view table");
                    int userChoice = Userin.nextInt();
                    Userin.nextLine();
                    if (userChoice != 1){ table = new JTableExample("Employees");}

                    switch (userChoice){

                        case 1:
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
                        case 2:

                            System.out.println("The table of Employees has been Displayed.\nChoose the id you want to delete : ");
                            int deleteUser = Userin.nextInt();

                            Userin.nextLine();

                            System.out.println("Are you sure you want to delete ID : " + deleteUser);
                            String confirmation = Userin.nextLine().toLowerCase();
                            if (confirmation.equals("yes")){
                                delete.deleteFromTable("Employees",deleteUser);
                                table.disposeWindow();
                            }
                            table = new JTableExample("Employees");

                            break;
                        case 3:
                            break;
                    }
                    break;

                    //Orders Switch
                case 2:
                    System.out.println("Do you want to \n1.insert\n2.delete\n3.view table");
                    userChoice = Userin.nextInt();
                    Userin.nextLine();
                    if (userChoice != 1){ table = new JTableExample("Orders");}

                    switch (userChoice) {
                        case 1:
                            System.out.println("Inserting data into Orders table:");
                            System.out.print("Enter ID: ");
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
                        case 2:
                            System.out.println("The table of Orders has been Displayed.\nChoose the id you want to delete : ");
                            int deleteUser = Userin.nextInt();
                            System.out.println("Are you sure you want to delete ID : " + deleteUser);
                            String confirmation = Userin.nextLine().toLowerCase();
                            if (confirmation.equals("yes")){
                                delete.deleteFromTable("Employees",deleteUser);

                                if (table != null){
                                    table.dispose();
                                }

                                table = new JTableExample("Orders");
                            }
                            break;
                        case 3:
                            break;

                    }
                    break;

                    //Stock Switch
                case 4:
                    System.out.println("Do you want to \n1.insert\n2.delete\n3.view table");
                    userChoice = Userin.nextInt();
                    Userin.nextLine();
                    if (userChoice != 1){ table = new JTableExample("Employees");}

                    switch (userChoice){
                        case 1:
                            System.out.println("Inserting data into Stock Items table:");
                            System.out.print("Enter Name: ");
                            String name = Userin.nextLine();

                            System.out.print("Enter Quantity in Stock: ");
                            int quantity = Userin.nextInt();

                            System.out.print("Enter Unit Price: ");
                            BigDecimal UnitP = Userin.nextBigDecimal();

                            System.out.print("Enter Sale Price: ");
                            BigDecimal SaleP = Userin.nextBigDecimal();

                            System.out.print("Enter Supplier ID: ");
                            int Supplier = Userin.nextInt();

                            System.out.println("Enter Aisle Num");
                            int Aisle = Userin.nextInt();

                            insert.insertIntoStockItems(name,quantity,UnitP,SaleP,Supplier,Aisle);
                            break;
                        case 2:
                            System.out.println("The table of Orders has been Displayed.\nChoose the id you want to delete : ");
                            int deleteUser = Userin.nextInt();
                            System.out.println("Are you sure you want to delete ID : " + deleteUser);
                            String confirmation = Userin.nextLine().toLowerCase();
                            if (confirmation.equals("yes")){
                                delete.deleteFromTable("Employees",deleteUser);

                                if (table != null){
                                    table.dispose();
                                }

                                table = new JTableExample("Orders");
                            }
                            break;
                        case 3:
                            break;

                    }
                    break;
                default:
                    System.out.println("Choice invalid. Try again");

            }
        }
        while(choice != 0);

        DatabaseConnector.disconnect();
        Userin.close();

    }
}

