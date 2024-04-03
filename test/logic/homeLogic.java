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
}
