/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.historiashardware;

import Swing.Interfaz;
import Swing.LoginDialog;
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
        //System.out.println(GestorAlmacenDAO.Prestamo(ele, u, con)); 
        
        
         java.awt.EventQueue.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            //sacar el login por encima de la interfaz
            LoginDialog login = new LoginDialog(interfaz, true);
            
            
            
            
            

            login.setVisible(true);
            
            //mandar el usuario a la interfaz principal
            Usuario user = login.getUser();
            interfaz.setUser(user);

            //sacar el menu cuando el usuario sea valido
            if (login.isValidado()) {
                interfaz.setVisible(true);
            } else {

                System.exit(0);
            }

        });

    }
}
