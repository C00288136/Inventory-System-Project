package panels;

import logic.homeLogic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class home extends JPanel {
    JLabel TotalSalesB = new JLabel();
    JLabel TotalProducts = new JLabel("Total  Products");
    JLabel TotalSupplier = new JLabel("Total Suppliers");
    JButton Add_item = new JButton("Add item");
    JButton Create_order = new JButton("Create order");

    GraphPanel graphPanel = new GraphPanel();
    homeLogic logic = new homeLogic();






//    labels
    JLabel recentOrder = new JLabel("Recent Order");
    JLabel bestSeller = new JLabel("Best selling item");

    public home(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        int graphwidth = 700;
        int graphheight = 400;

        graphPanel.setPreferredSize(new Dimension(graphwidth,graphheight));

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding


        add(graphPanel,gbc);


        // Set sizes and spaces for buttons
        JButton[] buttons = {Add_item, Create_order};
        int buttonWidth = 200;
        int buttonHeight = 70;
        int horizontalGap = 20;
        int verticalGap = 20;

        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            button.setBorder(BorderFactory.createCompoundBorder(
//                    lineborder for black outside empty border for padding
                    new LineBorder(Color.decode("#8764EC"), 2),
                    new EmptyBorder(20, 20, 20, 20)
            ));
            button.setContentAreaFilled(false);
            button.setBackground(Color.black);
        }

        JLabel[] labels = {TotalSalesB,TotalProducts,TotalSupplier,recentOrder,bestSeller};
        int labelWidth = 10;
        int labelHeight = 100;

        for (JLabel label : labels){
            label.setPreferredSize(new Dimension(labelHeight, labelHeight));
            label.setBorder(BorderFactory.createCompoundBorder(
//                    lineborder for black outside empty border for padding
                    new LineBorder(Color.decode("#8764EC"), 2),
                    new EmptyBorder(20, 20, 20, 20)
            ));
            label.setHorizontalAlignment(SwingConstants.CENTER);


        }

        gbc.insets = new Insets(verticalGap, horizontalGap, verticalGap, horizontalGap);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
//        html tags supported in swing
//        the value wouldn't display below using \n
        TotalSalesB.setText("<html>Total Sales<br>€" + logic.TotalSales() + "</html>");
        TotalSalesB.setHorizontalAlignment(SwingConstants.CENTER);
        add(TotalSalesB, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        TotalProducts.setText("<html>Total Products<br>" + logic.TotalProducts() + "</html>");
        add(TotalProducts, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        TotalSupplier.setText("<html>Total Suppliers<br>" + logic.TotalSales() + "</html>");
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
