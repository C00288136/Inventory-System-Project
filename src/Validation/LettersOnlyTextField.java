package Validation;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
    import javax.swing.JTextField;


public class LettersOnlyTextField extends JTextField {



        public LettersOnlyTextField(int columns) {
            super(columns);
        }

        @Override
        protected Document createDefaultModel() {
            return new LettersDocument();
        }

        private static class LettersDocument extends PlainDocument {

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


