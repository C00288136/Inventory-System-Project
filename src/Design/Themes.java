package Design;

import javax.swing.*;
import java.awt.*;

public class Themes {

    public static final Color PURPLE = Color.decode("#8764EC");

    public static final Font BUTTONFONT = new Font("Arial", Font.BOLD, 14);

    public static final Font TEXTFONT = new Font("Ari",Font.PLAIN, 14);

    public static final Color FONTCOLORBUTTON = Color.white;

    public static Color getColor() {
        return PURPLE;
    }

    public static Color getFontcolorbutton(){
        return FONTCOLORBUTTON;
    }
    public static Font getButtonfont() {
        return BUTTONFONT;
    }

    public static Font getTextfont() {
        return TEXTFONT;
    }

    public static void applyButtonTheme(JButton button){
        Font buttonFont = getButtonfont();
        Color buttonColor = getColor();
        Color TextColor = getFontcolorbutton();

        button.setFont(buttonFont);
        button.setBackground(buttonColor);
        button.setForeground(TextColor);
    }






}
