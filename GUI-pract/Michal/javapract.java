package Michal;

public class javapract {

    public static void main(String[] args) {
     /* 
        //JFrame =  a GUI window to add components 

        JFrame frame = new JFrame();// creates a frame
        frame.setTitle("Title goes here");// creates a title for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //exit out of application need exit on close, it will go into background
        frame.setResizable(false); //cannot resize window
        frame.setSize(420,420);// sets size of window 
        frame.setVisible(true);//makes frame visible 

        ImageIcon image = new ImageIcon("logo.png"); // create  a image icon 
        frame.setIconImage(image.getImage()); //changes icon of frame need the get image otherwise wont work
        frame.getContentPane().setBackground(Color.CYAN); //changes color or background uses default colors 
        //use new Color(0,0,0,) to set color as any in rgb range or use hex value 
*/

        
        // MyFrame myFrame = new MyFrame();
        new MyFrame(); //this works aswell but you cant make changes to the constructor
    }
}