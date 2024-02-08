import javax.swing.JOptionPane;
// JOptionPane is a class library that allows us to return a simple dialogue box that will either ask for or return information
public class gui
{
    public static void main(String[] args)
    {
        
        String inp1 = JOptionPane.showInputDialog("Please enter your first number");
        String inp2 = JOptionPane.showInputDialog("Please enter your second number");

        //showInputDialog method always returns a string so parsing is essential

        int num1 = Integer.parseInt(inp1);
        int num2 = Integer.parseInt(inp2);

        int sum = num1+num2;

        JOptionPane.showMessageDialog(null, "The sum is " + sum,"Sum of two Integers: ", JOptionPane.PLAIN_MESSAGE);
    }
}