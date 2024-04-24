package panels;

import dbCon.DatabaseConnector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Graph panel class used to create the graph in the home panel
 */
public class GraphPanel extends ChartPanel {
    Connection connection;
    /**
     * Graph panel used to display data from the table using the JFreeChart Library
     */
    public GraphPanel () {
        super(null);
        connection = DatabaseConnector.connect();
        try {
            //statement for retrieving the information used for the table
            PreparedStatement pstat = connection.prepareStatement("SELECT MONTHNAME(SaleDate) AS Month, SUM(TotalPrice) AS Total_Sales FROM sales GROUP BY MONTH(SaleDate), MONTHNAME(SaleDate) ORDER BY MONTH(SaleDate);");
            ResultSet resultSet = pstat.executeQuery();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while (resultSet.next()){
                String month = resultSet.getString("Month");
                double totalSales = resultSet.getDouble("Total_Sales");
                dataset.addValue(totalSales,"Sales",month);
            }
        //line chart gotten from the JFreeChart Library
        JFreeChart lineChart = ChartFactory.createLineChart("Sales over past 3 months", "Months", "Sales", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);

        //changes the thickness of the line rendered for the data
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setSeriesStroke(0,new BasicStroke(3.0f));

            this.setChart(lineChart);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
