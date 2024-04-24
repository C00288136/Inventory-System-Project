package Design;

import javax.swing.*;
import java.awt.*;

/**
 * Themes Class used for consisdent use of the same colours and fonts
 */
public class Themes {

    public static final Color PURPLE = Color.decode("#8764EC");

    public static final Font BUTTONFONT = new Font("Arial", Font.BOLD, 14);

    public static final Font TEXTFONT = new Font("Ari",Font.PLAIN, 14);

    public static final Color FONTCOLORBUTTON = Color.white;

    /**
     *
     * @return - returns Priamry Colour for the Panels
     */
    public static Color getColor() {
        return PURPLE;
    }

    /**
     *
     * @return - returns font colour for the buttons
     */
    public static Color getFontcolorbutton(){
        return FONTCOLORBUTTON;
    }

    /**
     *
     * @return - returns the Font for the buttons
     */
    public static Font getButtonfont() {
        return BUTTONFONT;
    }

    /**
     *
     * @return - Returns the font used for all text in the panels
     */
    public static Font getTextfont() {
        return TEXTFONT;
    }

    /**
     * method for applying all the necessary colours and fonts to a button
     * @param button - the button which the font and colour will change
     */
    public static void applyButtonTheme(JButton button){
        Font buttonFont = getButtonfont();
        Color buttonColor = getColor();
        Color TextColor = getFontcolorbutton();

        button.setFont(buttonFont);
        button.setBackground(buttonColor);
        button.setForeground(TextColor);
    }






}
