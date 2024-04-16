package logic;

import dbCon.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class homeLogic {

    DatabaseConnector db = new DatabaseConnector();


    public double TotalSales(){
        Connection connection = DatabaseConnector.connect();
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        double totalSales = 0;
        try{
            pstat = connection.prepareStatement("SELECT SUM(TotalPrice) as Total from sales");
            resultSet = pstat.executeQuery();

            if (resultSet.next()){
                totalSales = resultSet.getDouble("Total");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
            if (resultSet != null){
                resultSet.close();
            }
            if (pstat != null){
                pstat.close();
            }
            connection.close();
        }

        catch (SQLException e){
            e.printStackTrace();
        }
            }
        return totalSales;
    }


public int TotalProducts(){
    Connection connection = DatabaseConnector.connect();
    PreparedStatement pstat = null;
    ResultSet resultSet = null;
    int totalProducts = 0;
    try{
        pstat = connection.prepareStatement("SELECT COUNT(product_ID) as Products from stock_items;");
        resultSet = pstat.executeQuery();

        if (resultSet.next()){
            totalProducts = resultSet.getInt("Products");
        }

    }
    catch (SQLException e){
        e.printStackTrace();
    }finally {
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (pstat != null){
                pstat.close();
            }
            connection.close();
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }
    return totalProducts;
}

public int TotalSuppliers(){
    Connection connection = DatabaseConnector.connect();
    PreparedStatement pstat = null;
    ResultSet resultSet = null;
    int totalSuppliers = 0;
    try{
        pstat = connection.prepareStatement("SELECT COUNT(supplier_ID) as Suppliers from stock_items;");
        resultSet = pstat.executeQuery();

        if (resultSet.next()){
            totalSuppliers = resultSet.getInt("Total");
        }

    }
    catch (SQLException e){
        e.printStackTrace();
    }finally {
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (pstat != null){
                pstat.close();
            }
            connection.close();
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }
    return totalSuppliers;
}

public int recentOrder() {
    Connection connection = DatabaseConnector.connect();
    int orderID = 0;

    try {
        PreparedStatement statement = connection.prepareStatement("SELECT Order_ID FROM Orders WHERE OrderDate = (SELECT MAX(OrderDate) FROM Orders)");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            orderID = resultSet.getInt("Order_ID");
        }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orderID;
}
public String BestSelling(){
    Connection con = DatabaseConnector.connect();   
    String Best = "";

    try{
        PreparedStatement stat = con.prepareStatement("SELECT Stock_ID FROM ( SELECT Stock_ID, SUM(Quantity) AS TotalQuantity FROM sales GROUP BY Stock_ID ORDER BY TotalQuantity DESC ) AS BestSellingItem;");
        ResultSet rs = stat.executeQuery();

        int bestSellingID = 0;
        if (rs.next()){
            bestSellingID = rs.getInt("Stock_ID");
        }

        try{
            PreparedStatement statement = con.prepareStatement("Select name from Stock_Items where product_ID = (?)");
            statement.setInt(1, bestSellingID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Best = resultSet.getString("name");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    return Best;
}
}
