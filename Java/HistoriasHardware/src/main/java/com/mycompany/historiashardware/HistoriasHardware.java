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
           //UbicacionDAO.insertar(con);
          
           //List<Elemento> inv = GestorAlmacenDAO.devolverInventarioCompleto(con);
           //for(Elemento e: inv){
             //  System.out.println(e);
           //}
           Administrador a=new Administrador("miguel25","Miguel","miguel2525");
           Profesor p=new Profesor("saul25","Saúl","saul2525");
           Profesor p2=new Profesor("miguel555","Michel","miguel2552");
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
        /*SwingUtilities.invokeLater(() -> {

            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });*/
    }
}
