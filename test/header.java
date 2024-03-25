
import javax.swing.*;
import java.awt.*;
public class header extends JPanel {

    private ImageIcon resizeIcon(ImageIcon imageIcon, int width, int height){
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    ImageIcon logo = new ImageIcon("assets/logo.png");
    ImageIcon resizedLogo = resizeIcon(logo,50,50);
    JLabel logoLabel = new JLabel(resizedLogo);
    ImageIcon user = new ImageIcon("assets/profile.png");
    ImageIcon resizedUser = resizeIcon(user,60,60);
    JLabel userLabel = new JLabel(resizedUser);


    JLabel businessName = new JLabel("Inventory System Portal");

    public header(){
        int panelHeight = 70;
        setPreferredSize(new Dimension(0,panelHeight));
        setLayout(new BorderLayout());
        add(logoLabel,BorderLayout.WEST);
        add(userLabel,BorderLayout.EAST);
        businessName.setHorizontalAlignment(SwingConstants.CENTER);
        add(businessName,BorderLayout.CENTER);

        setVisible(true);
    }



}
