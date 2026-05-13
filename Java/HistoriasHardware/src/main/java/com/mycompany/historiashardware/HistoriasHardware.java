/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.historiashardware;

import Swing.Swing;
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

         // Usuario u = new Administrador("eduaro25", "Eduardo", "1234");
          //UsuariosDAO.insertar(con, u);
          Usuario u = UsuariosDAO.Login("eduaro25","1234",con);
          if(u!= null){
              System.out.println("Login funciona");
          }
          /*SwingUtilities.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });*/
    }
}
