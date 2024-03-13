

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class home extends JPanel {
    JButton TotalSalesB = new JButton("Total Sales");
    JButton TotalProducts = new JButton("Total  Products");
    JButton TotalSupplier = new JButton("Total Suppliers");
    JButton Add_item = new JButton("Add item");
    JButton Create_order = new JButton("Create order");

    JPanel graphPanel = new JPanel();



//    labels
    JLabel recentOrder = new JLabel("Recent Order");
    JLabel bestSeller = new JLabel("Best selling item");

    public home(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon graph = new ImageIcon("assets/graph.jpeg");
        JLabel imageLabel = new JLabel(graph);
        int imgwidth = 700;
        int imgheight = 400 ;

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
        imageLabel.setPreferredSize(new Dimension(imgwidth,imgheight));
        gbc.gridwidth = 5;
        gbc.gridheight = 1;


//        int tablewidth = 500;
//        int tableheight = 500;
//        TablePanel.setPreferredSize(new Dimension(tablewidth,tableheight));
//        gbc.gridwidth = 5;
//        gbc.gridheight = 1;

        graphPanel.add(imageLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;


//        graphPanel.add(TablePanel);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        add(graphPanel,gbc);


        // graphPanel.add(imageLabel);
        add(graphPanel, gbc);

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
        add(TotalSalesB, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(TotalProducts, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        add(TotalSupplier, gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        add(recentOrder, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        add(bestSeller, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(Add_item, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        add(Create_order, gbc);
        add(Create_order,gbc);

        setVisible(true);
    }

}
