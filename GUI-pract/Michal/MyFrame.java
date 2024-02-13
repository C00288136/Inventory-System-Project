package Michal;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    MyFrame(){
         //JFrame =  a GUI window to add components 

        JFrame frame = new JFrame();// creates a frame
        this.setTitle("Title goes here");// creates a title for this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //exit out of application need exit on close, it will go into background
        this.setResizable(false); //cannot resize window
        this.setSize(420,420);// sets size of window 
        this.setVisible(true);//makes this visible 

        ImageIcon image = new ImageIcon("logo.png"); // create  a image icon 
        //root path will take images from project folder if its in a seperate folder a relative path is needed
        
        this.setIconImage(image.getImage()); //changes icon of this need the get image otherwise wont work
        this.getContentPane().setBackground(Color.CYAN); //changes color or background uses default colors 
        //use new Color(0,0,0,) to set color as any in rgb range or use hex value 
    }
    
}
