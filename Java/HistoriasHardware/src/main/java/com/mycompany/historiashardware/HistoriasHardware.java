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

           System.out.println("Insertar: ");
           UsuariosDAO.insertar(con, a);
           UsuariosDAO.insertar(con, p);
           System.out.println("Modificar: ");
           UsuariosDAO.modificar(con,"miguel25",p2 );
           System.out.println("Mostrar: ");
           HashSet<Usuario> lista=UsuariosDAO.devolverUsuarios(con);
           for (Usuario usuario : lista) {
               System.out.println(usuario.toString());
        }
           System.out.println("Borrar: ");
           UsuariosDAO.borrar(con, a.getId_usuario());
           UsuariosDAO.borrar(con, p.getId_usuario());
           System.out.println("Fin: ");
           
          
          //Elemento u = new Elemento(0, "Teste1", "Testeoooo", "Hardware", Estado.PRESTADO, new Ubicacion(1,"nn","Valda","g"));
          //ElementoDAO.insertarElemento(con, u);
          //ElementoDAO.eliminarElemento(con, 6);
          //ElementoDAO.actualizarEstadoElemento(con, 5, Estado.EN_REPARACION.toString());
          
          
          /*SwingUtilities.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });*/
    }
}
