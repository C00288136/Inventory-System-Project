import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;


public class GraphPanel extends JPanel {

    int graphwidth = 700;
    int graphheight = 400;
    ImageIcon graph = new ImageIcon("assets/graph.jpeg");
    JLabel imageLabel = new JLabel(graph);
    int imgwidth = 700;
    int imgheight = 400 ;




    public GraphPanel(){
        setPreferredSize(new Dimension(graphwidth,graphheight));


    }


    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(50,"Profit","January");
        dataset.setValue(40,"Profit","Feb");
        dataset.setValue(20,"Profit","March");
        dataset.setValue(60,"Profit","May");
        JFreeChart chart = ChartFactory.createBarChart("Sales over past 3 months","Months","Sales",dataset, PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot p=chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        GraphPanel graphPanel = new GraphPanel();
        ChartFrame frame = new ChartFrame("Bar chart for sales over last 3 months",chart);
        frame.setVisible(true);
        frame.setSize(450,350);

    }
}
