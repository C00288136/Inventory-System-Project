package panels;

import dbCon.DatabaseConnector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GraphPanel extends ChartPanel {
    Connection connection;

    int graphwidth = 700;
    int graphheight = 400;
    ImageIcon graph = new ImageIcon("assets/graph.jpeg");
    JLabel imageLabel = new JLabel(graph);
    int imgwidth = 700;
    int imgheight = 400 ;






    public GraphPanel () {
        super(null);

        connection = DatabaseConnector.connect();
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try {
            pstat = connection.prepareStatement("SELECT MONTHNAME(SaleDate) AS Month, SUM(TotalPrice) AS Total_Sales FROM sales GROUP BY MONTH(SaleDate) ORDER BY MONTH(SaleDate)");
            resultSet = pstat.executeQuery();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while (resultSet.next()){
                String month = resultSet.getString("Month");
                double totalSales = resultSet.getDouble("Total_Sales");
                dataset.addValue(totalSales,"Sales",month);
            }

        JFreeChart lineChart = ChartFactory.createLineChart("Sales over past 3 months", "Months", "Sales", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);

            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setSeriesStroke(0,new BasicStroke(3.0f));

            this.setChart(lineChart);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }



    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GraphPanel graphPanel = new GraphPanel();
        frame.add(graphPanel);
        frame.setVisible(true);
        frame.setSize(1000,500);

    }
}
