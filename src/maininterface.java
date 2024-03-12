import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    JPanel TablePanel = new JPanel();

    JPanel body = new JPanel();
    JPanel header = new JPanel();

    //labels
    JLabel recentOrder = new JLabel("Recent Order");
    JLabel bestSeller = new JLabel("Best selling item");


    public maininterface() {
        frame.setSize(1600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(nav, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);


        body.setBackground(Color.red);
        header.setBackground(Color.green);

//        header settings
        int height = 100;
        header.setPreferredSize(new Dimension(0,height));

// nav settings
        nav.setBackground(Color.decode("#8764EC"));
        JButton[] navbuttons = {home, orders, sales, salesReport, Inventory, settings};
        int navbuttonWidth = 120;
        int navbuttonHeight = 40;
        int navverticalGap = 10;
        nav.setLayout(new GridLayout(0,1));


        for (JButton button : navbuttons) {
            button.setPreferredSize(new Dimension(navbuttonWidth, navbuttonHeight));
            button.setContentAreaFilled(false);
            button.setBorderPainted(true);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.setForeground(Color.WHITE);

            nav.add(button);
        }


//        TODO create the grid body layout, add graphs and buttons
//        main menu layout
body.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

/*ImageIcon graph = new ImageIcon("assets/graph.jpeg");
JLabel imageLabel = new JLabel(graph);
int imgwidth = 700;
int imgheight = 400 ;

<<<<<<< HEAD
        /* Set border for graphPanel */
        graphPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.black),
                        BorderFactory.createEmptyBorder(5,5,5,5)
                ),
                BorderFactory.createLineBorder(Color.gray,2)
        ));

        int graphwidth = 700;
        int graphheight = 400;
        graphPanel.setPreferredSize(new Dimension(graphwidth,graphheight));
=======
imageLabel.setPreferredSize(new Dimension(imgwidth,imgheight));
gbc.gridwidth = 5;
gbc.gridheight = 1;*/

int tablewidth = 500;
int tableheight = 500;
TablePanel.setPreferredSize(new Dimension(tablewidth,tableheight));
gbc.gridwidth = 5;
gbc.gridheight = 1;
>>>>>>> 8bdb75c1da6f07b9885c87e130c6bc1d28224343

/*graphPanel.add(imageLabel);
gbc.gridx = 0;
gbc.gridy = 0;*/

<<<<<<< HEAD
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
=======
graphPanel.add(TablePanel);
gbc.gridx = 0;
gbc.gridy = 0;
body.add(graphPanel,gbc);
>>>>>>> 8bdb75c1da6f07b9885c87e130c6bc1d28224343


        graphPanel.add(imageLabel);
        body.add(graphPanel, gbc);

        // Set sizes and spaces for buttons
        JButton[] buttons = {TotalSalesB, TotalProducts, TotalSupplier, Add_item, Create_order};
        int buttonWidth = 200;
        int buttonHeight = 100;
        int horizontalGap = 20;
        int verticalGap = 20;

        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            button.setBorder(new EmptyBorder(10,12,15,12));
            button.setContentAreaFilled(false);
            button.setBackground(Color.black);


        }

        gbc.insets = new Insets(verticalGap, horizontalGap, verticalGap, horizontalGap);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        body.add(TotalSalesB, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        body.add(TotalProducts, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        body.add(TotalSupplier, gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        body.add(recentOrder, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
<<<<<<< HEAD
        body.add(bestSeller, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        body.add(Add_item, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        body.add(Create_order, gbc);
=======
body.add(Create_order,gbc);

//Action Listener

Inventory.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Create and show JTableExample in the body panel
                JTableExample table = new JTableExample("stock_items");

                TablePanel.removeAll(); // Clear existing components in TablePanel
                TablePanel.add(table);
                TablePanel.revalidate(); // Refresh the layout
                TablePanel.repaint(); // Repaint the panel
             
    
           //TODO implement JTableExample class to appear as panel in gui
        }
    });

sales.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                // Create and show JTableExample in the body panel
                JTableExample table = new JTableExample("sales");

                TablePanel.removeAll(); // Clear existing components in TablePanel
                TablePanel.add(table);
                TablePanel.revalidate(); // Refresh the layout
                TablePanel.repaint(); // Repaint the panel
                

                //TODO implement JTableExample class to appear as panel in gui
        }
});

orders.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Create and show JTableExample in the body panel
                JTableExample table = new JTableExample("orders");

                TablePanel.removeAll(); // Clear existing components in TablePanel
                TablePanel.add(table);
                TablePanel.revalidate(); // Refresh the layout
                TablePanel.repaint(); // Repaint the panel
             
    
           //TODO implement JTableExample class to appear as panel in gui
        }
    });

    
    
>>>>>>> 8bdb75c1da6f07b9885c87e130c6bc1d28224343

//!!!!! ALWAYS SET VISIBILITY LAST
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        maininterface window =new maininterface();

    }
}
