package Michal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyFrame2 extends JFrame implements ActionListener {
    
    JButton button; // have to make it global so in can be used outside constructor
    JLabel label;


    MyFrame2(){
        

        ImageIcon image = new ImageIcon("Michal/gun.png");
        ImageIcon image2 = new ImageIcon("Michal/chip.png");

        label = new JLabel();
        label.setIcon(image2);
        label.setBounds(150,250,150,150);
        label.setVisible(false);


        button = new JButton();
        button.setBounds(200,100,250,100);// x,y width , height
        button.addActionListener(this);
        button.setText("This is a button");
        button.setFocusable(false);
        button.setIcon(image);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans " ,Font.BOLD,25));
        button.setIconTextGap(-15);
        button.setForeground(Color.BLUE);
        button.setBackground(Color.red);
        button.setBorder(BorderFactory.createEtchedBorder());
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(button);
        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() ==button){
            System.out.println("wow");
            label.setVisible(true);

        }
    }
}
