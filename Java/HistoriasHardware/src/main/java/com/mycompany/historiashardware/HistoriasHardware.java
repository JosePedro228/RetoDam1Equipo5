/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.historiashardware;

import Swing.Interfaz;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAM112
 */
public class HistoriasHardware {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
        
            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });
    }
}
