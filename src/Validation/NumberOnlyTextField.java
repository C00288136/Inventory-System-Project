package Validation;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class NumberOnlyTextField extends JTextField {

    /**
     * A text field which only allows the user the enter Numbers into
     * @param columns - amount of numbers the user is allowed to enter
     */
    public NumberOnlyTextField(int columns) {
        super(columns);
    }

    @Override
    protected Document createDefaultModel() {
        return new NumericDocument();
    }
//        overrides the default model for the document to change the input type

    private static class NumericDocument extends PlainDocument {

        //            method overrides the method inside the plainDocument class which allows for data input into textFields
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            // Check if the string contains only digits
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return;
                }
            }

            // If all characters are digits, insert the string into the document
            super.insertString(offs, str, a);
        }
    }
}

