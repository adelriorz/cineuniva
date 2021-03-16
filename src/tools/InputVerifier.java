package tools;

/**
 *
 * @author Armando Del Rio
 */
//import javax.swing.InputVerifier;
import javax.swing.JTextField;

public class InputVerifier {
    
    protected boolean verifyField(InputVerifier verifier, JTextField input) {
        return verifier.verify(input);
    }

    private boolean verify(JTextField input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
