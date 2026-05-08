/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.historiashardware;

import Swing.Interfaz;
import bbdd.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAM112
 */
public class HistoriasHardware {

    public static void main(String[] args) {
        try {
            Statement stmt = ConnectionDB.openConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }
        SwingUtilities.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });
    }
}
