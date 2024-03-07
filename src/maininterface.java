import javax.swing.*;
import java.awt.*;

public class maininterface {
//initialise frame buttons and panels

    JFrame frame = new JFrame("Inventory Main Menu");

    JButton TotalSalesB = new JButton("Total Sales");
    JButton TotalProducts = new JButton("Total  Products");
    JButton TotalSupplier = new JButton("Total Suppliers");
    JButton Add_item = new JButton("Add item");
    JButton Create_order = new JButton("Create order");

    //nav bar
    JButton home = new JButton("Home");
    JButton orders = new JButton("Orders");
    JButton sales = new JButton("Sales");
    JButton salesReport = new JButton("Sales Report");
    JButton Inventory = new JButton("Inventory");
    JButton settings = new JButton("Settings");

    JPanel nav = new JPanel();

    JPanel graphPanel = new JPanel();

    JPanel body = new JPanel();
    JPanel header = new JPanel();

    //labels
    JLabel recentOrder = new JLabel("Recent Order");
    JLabel bestSeller = new JLabel("Best selling item");


    public maininterface() {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(nav, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);

        nav.setBackground(Color.blue);
        body.setBackground(Color.red);
        header.setBackground(Color.green);

//        nav settings
        nav.add(home);
        nav.add(orders);
        nav.add(sales);
        nav.add(salesReport);
        nav.add(Inventory);
        nav.add(settings);
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));

//        TODO create the grid body layout, add graphs and buttons
//        main menu layout
body.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

ImageIcon graph = new ImageIcon("assets/graph.jpeg");
JLabel imageLabel = new JLabel(graph);
int imgwidth = 500;
int imgheight = 300 ;

imageLabel.setPreferredSize(new Dimension(imgwidth,imgheight));
gbc.gridwidth = 5;
gbc.gridheight = 1;


graphPanel.add(imageLabel);
gbc.gridx = 0;
gbc.gridy = 0;
body.add(graphPanel,gbc);

gbc.gridheight = 1;
gbc.gridwidth = 1;

gbc.gridx = 0;
gbc.gridy = 2;
body.add(TotalSalesB,gbc);

gbc.gridx = 2;
gbc.gridy = 2;
body.add(TotalProducts,gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
body.add(TotalSupplier,gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
body.add(Add_item,gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
body.add(Create_order,gbc);




//!!!!! ALWAYS SET VISIBILITY LAST
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        maininterface window =new maininterface();

    }
}
