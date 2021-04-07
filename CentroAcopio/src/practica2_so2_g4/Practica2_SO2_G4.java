/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_so2_g4;

import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Practica2_SO2_G4{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Interface frame = new Interface();
        frame.startExecution();
        JOptionPane.showMessageDialog(null, "Centro de Acopio cerrado.", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
    }
}
