package panels;

import Design.Themes;
import logic.homeLogic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * Class which creates the home page panel for the portal
 */
public class home extends JPanel {
    JLabel TotalSalesB = new JLabel();
    JLabel TotalProducts = new JLabel("Total  Products");
    JLabel TotalSupplier = new JLabel("Total Suppliers");
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


      
        int horizontalGap = 20;
        int verticalGap = 20;

       
        JLabel[] labels = {TotalSalesB,TotalProducts,TotalSupplier,recentOrder,bestSeller};
        int labelWidth = 10;
        int labelHeight = 100;

        for (JLabel label : labels){
            label.setPreferredSize(new Dimension(labelWidth, labelHeight));
            label.setBorder(BorderFactory.createCompoundBorder(
//                    lineborder for black outside empty border for padding
                    new LineBorder(Color.decode("#8764EC"), 2),
                    new EmptyBorder(20, 20, 20, 20)
            ));

            label.setFont(Themes.getButtonfont());
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
        TotalSupplier.setText("<html>Total Suppliers<br>" + logic.TotalSuppliers() + "</html>");
        add(TotalSupplier, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        recentOrder.setText("Recent Order ID:" + logic.recentOrder());
        add(recentOrder, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        bestSeller.setText("Best Selling Item: " + logic.BestSelling());
        add(bestSeller, gbc);

        setVisible(true);
    }
}
