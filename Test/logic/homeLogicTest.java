package logic;

import dbCon.DatabaseConnector;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class homeLogicTest {
    Connection connection = DatabaseConnector.connect();
    @Test
    void totalSales() {
        double TotalInTest = 0;
        try {
            PreparedStatement pstat = connection.prepareStatement("SELECT SUM(TotalPrice) as Total from sales");
            ResultSet resultSet = pstat.executeQuery();

            if (resultSet.next()){
                TotalInTest = resultSet.getDouble("Total");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        homeLogic homeLogic = new homeLogic();

        double TotalTested = homeLogic.TotalSales();

        assertEquals(TotalTested,TotalInTest);
    }

    @Test
    void totalProducts() {
        int TotalProductsInTest = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(product_ID) as Products from stock_items;");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                TotalProductsInTest = resultSet.getInt("Products");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        var homelogic = new homeLogic();
        int TotalProductsTested = homelogic.TotalProducts();

        assertEquals(TotalProductsTested,TotalProductsInTest);


    }
}