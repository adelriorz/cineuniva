package tools;

//import javax.swing.InputVerifier;
import javax.swing.JTextField;
/*
**Written by: Armando Del Río Ramírez & Paola Escalera
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code for input verifier
*/
public class InputVerifier {
    
    protected boolean verifyField(InputVerifier verifier, JTextField input) {
        return verifier.verify(input);
    }

    private boolean verify(JTextField input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
