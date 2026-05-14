/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.historiashardware;

import Swing.Interfaz;
import Usuarios.Administrador;
import Usuarios.Profesor;
import Usuarios.Usuario;
import bbdd.ConnectionDB;

import bbdd.GestorAlmacenDAO;
import bbdd.UbicacionDAO;
import bbdd.UsuariosDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author DAM112
 */
public class HistoriasHardware {

    public static void main(String[] args) throws SQLException {
        
        Connection con = ConnectionDB.openConnection();
        Elemento ele = new Elemento(1, "f", "g", "Ha", Estado.PRESTADO, new Ubicacion(0, "f", "f", "f"));
        Usuario u = new Usuario("eduaro25", "eduaro25", "1234");
        System.out.println(GestorAlmacenDAO.Prestamo(ele, u, con)); 
        
        
          /*SwingUtilities.invokeLater(() -> {
          
              Interfaz interfaz = new Interfaz();
              interfaz.setVisible(true);
          });
          */
    }
}
