package Michal;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panels {

    public static void main(String[] args) {

      JLabel label = new JLabel();
      label.setText("Hi");
      

        JPanel redpanel = new JPanel();
        redpanel.setBackground(Color.red);
        redpanel.setBounds(0,0,100,100);

        JPanel bluepanel = new JPanel();
        bluepanel.setBackground(Color.blue);
        bluepanel.setBounds(100,0,100,100);
        
        JPanel greenpanel = new JPanel();
        greenpanel.setBackground(Color.green);
        greenpanel.setBounds(0,100,200,100);


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


    }


    
}
