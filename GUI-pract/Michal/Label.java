package Michal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Label {

    public static void main(String[] args) {

        ImageIcon image = new ImageIcon("GUI-pract/Michal/logo.png");
        Border border = BorderFactory.createLineBorder(Color.green,3);

        JLabel label = new JLabel(); //create a label
        label.setText("First label for program"); // set text of label
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER); // overlaps image LEFT,RIGHT of imageicon
        label.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER , BOTTOM of imageicon
        label.setForeground(Color.green);
        label.setFont(new Font("MV Boli",Font.PLAIN,20)); //set font for text, decoration and size
        label.setIconTextGap(20); //sets a gap of text to image
        label.setBackground(Color.gray); //set color background
        label.setOpaque(true); //display background color
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER); // sets vertical position of icon+text within label
        label.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within label
        // label.setBounds(100,0,500,500); //sets the x,y position within frame as well as dimensions


        
        JFrame frame = new JFrame();// creates a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit out of application need exit on close, it will go into background
        // frame.setSize(1000,1000);// sets size of window 
        // frame.setLayout(null);
        frame.setVisible(true);//makes frame visible 
        frame.add(label); //IMPORTANT to add label to frame
        frame.pack();
         //resize frame to the size needed to fit all components !!make sure components are there before you pack





    }
    
}
