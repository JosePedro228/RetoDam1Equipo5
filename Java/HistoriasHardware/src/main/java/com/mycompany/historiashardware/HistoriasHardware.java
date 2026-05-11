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
           UbicacionDAO.insertar(con);
          
           List<Elemento> inv = GestorAlmacenDAO.devolverInventarioCompleto(con);
           for(Elemento e: inv){
               System.out.println(e);
           }
           Administrador a=new Administrador("Miguel","miguel25","miguel2525",1);
           Profesor p=new Profesor("Saúl","saul25","saul2525",2);
           System.out.println("Insertar: ");
           UsuariosDAO.insertar(con, a);
           UsuariosDAO.insertar(con, p);
           System.out.println("Mostrar: ");
           HashSet<Usuario> lista=UsuariosDAO.devolverUsuarios(con);
           for (Usuario usuario : lista) {
               System.out.println(usuario.toString());
        }
           System.out.println("Borrar: ");
           UsuariosDAO.borrar(con, a.getId_usuario());
           UsuariosDAO.borrar(con, p.getId_usuario());
           System.out.println("Fin: ");
        /*SwingUtilities.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });*/
    }
}
