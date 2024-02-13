import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LabelFrame extends JFrame 
{
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public LabelFrame()
    {
        super("Testing JLabel");
        setLayout(new FlowLayout());
        label1 = new JLabel("Label with Text");
        label1.setToolTipText("This is label1");
        add(label1);
        Icon car = new ImageIcon(getClass().getResource("car.jpeg"));
    }
}
