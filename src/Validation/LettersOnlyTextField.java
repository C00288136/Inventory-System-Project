package Validation;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
    import javax.swing.JTextField;


public class LettersOnlyTextField extends JTextField {


    /**
     * A new textField class which only allows the user to enter Letters
     * @param columns - number of characters the user is allowed to enter
     */
        public LettersOnlyTextField(int columns) {
            super(columns);
        }

//        overrides the default model for the document to change the input type
        @Override
        protected Document createDefaultModel() {
            return new LettersDocument();
        }

        private static class LettersDocument extends PlainDocument {

//            method overrides the method inside the plainDocument class which allows for data input into textFields
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null) {
                    return;
                }

                // Check if the string contains only digits
                for (char c : str.toCharArray()) {
                    if (!Character.isLetter(c)) {
                        return;
                    }
                }

                // If all characters are digits, insert the string into the document
                super.insertString(offs, str, a);
            }
        }




}


