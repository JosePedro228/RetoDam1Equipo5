/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.historiashardware;

import Swing.Interfaz;
import bbdd.ConnectionDB;
import bbdd.GestorAlmacenDAO;
import bbdd.UbicacionDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAM112
 */
public class HistoriasHardware {

    public static void main(String[] args) throws SQLException {
        
          Connection con = ConnectionDB.openConnection();
          // UbicacionDAO.insertar(con);
          
           List<Elemento> inv = GestorAlmacenDAO.devolverInventarioCompleto(con);
           for(Elemento e: inv){
               System.out.println(e);
           }
           
        /*SwingUtilities.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });*/
    }
}
