package tools;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


//    Set instance of class
//    JTextField editor = new JTextField(20);
//    editor.setInputVerifier(new Verifier());

public class Verifier extends InputVerifier {
    
    public boolean verify(JComponent editor) {

    if (editor instanceof JTextField){
        String text = ((JTextField)editor).getText();

            try{
                Integer.parseInt(text);
                return true;
            } catch (Exception e){
                JOptionPane.showMessageDialog(editor, "Not a number");
                return false;
            }
        }
        return true;
    }
}
