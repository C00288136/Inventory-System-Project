package panels;

import Design.Themes;

import javax.swing.*;
import java.awt.*;

/**
 * Class which creates the header used for the portal
 */
public class header extends JPanel {

    /**
     * method which is used to rescale the size of a image to a desired amount
     * @param imageIcon - image for rescaling
     * @param width - Desired with after the rescaling
     * @param height - Desired Height after the rescaling
     * @return - Images scailed to the specified size
     */
    public ImageIcon resizeIcon(ImageIcon imageIcon, int width, int height){
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    ImageIcon logo = new ImageIcon(getClass().getResource("/assets/Designer.jpeg"));
    ImageIcon resizedLogo = resizeIcon(logo,70,70);
    JLabel logoLabel = new JLabel(resizedLogo);
    ImageIcon user = new ImageIcon(getClass().getResource("/assets/profile.png"));
    ImageIcon resizedUser = resizeIcon(user,60,70);
    JLabel userLabel = new JLabel(resizedUser);


    JLabel businessName = new JLabel("Inventory System Portal");
    Font font = Themes.getButtonfont();


    public header(){
        int panelHeight = 70;
        setPreferredSize(new Dimension(0,panelHeight));
        setLayout(new BorderLayout());
        add(logoLabel,BorderLayout.WEST);
        add(userLabel,BorderLayout.EAST);
        businessName.setHorizontalAlignment(SwingConstants.CENTER);
        businessName.setFont(font);
        add(businessName,BorderLayout.CENTER);

        setVisible(true);
    }



}
