package Michal;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panels {

    public static void main(String[] args) {

      ImageIcon image = new ImageIcon("Michal/gun.png");

      JLabel label = new JLabel();
      label.setText("Hi");
      label.setIcon(image); 
      label.setVerticalAlignment(JLabel.BOTTOM);
      label.setHorizontalAlignment(JLabel.RIGHT);
      

        JPanel redpanel = new JPanel();
        redpanel.setBackground(Color.red);
        redpanel.setBounds(0,0,200,100);

        JPanel bluepanel = new JPanel();
        bluepanel.setBackground(Color.blue);
        bluepanel.setBounds(200,0,200,100);
        
        JPanel greenpanel = new JPanel();
        greenpanel.setBackground(Color.green);
        greenpanel.setBounds(0,100,400,100);
        greenpanel.setLayout(new BorderLayout());


          JFrame frame = new JFrame();// creates a frame
        frame.setTitle("Title goes here");// creates a title for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //exit out of application need exit on close, it will go into background
        frame.setLayout(null);
        frame.setResizable(false); //cannot resize window
        frame.setSize(700,700);// sets size of window 
        frame.setVisible(true);//makes frame visible 

        
        frame.add(redpanel);
        frame.add(bluepanel);
        frame.add(greenpanel);

        greenpanel.add(label); 

        

    }


    
}
