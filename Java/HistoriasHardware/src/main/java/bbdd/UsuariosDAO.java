/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import Usuarios.Administrador;
import Usuarios.Profesor;
import java.sql.Connection;
import java.sql.*;
import Usuarios.Usuario;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM116
 */
public class UsuariosDAO {

    Set<Usuario> listaUsuarios;

    public UsuariosDAO() {
        this.listaUsuarios = new HashSet<Usuario>();
    }

    public static int insertar(Connection con, Usuario u) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        if (u != null && !buscar(con, u.getId_usuario())) {
            String sql = "INSERT INTO usuarios (id_usuario, nombre, contraseña, id_rol) VALUES (?,?,?,?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, u.getId_usuario());
                ps.setString(2, u.getNombre());
                ps.setString(3, u.getContrasenia());
                if (u instanceof Profesor) {
                    ps.setString(4, "2");
                } else {
                    ps.setString(4, "1");
                }
                int valor = ps.executeUpdate();
                if (valor == 0) {
                    resultado = -1;
                    System.out.println("Error, no se pudo insertar el usuario.");
                } else {
                    resultado = 0;
                    System.out.println("Usuario agregado correctamente.");
                }

            } catch (SQLException e) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        ps.close();
        ConnectionDB.closeConnection();
        return resultado;
    }

    public static void modificar(Connection con, String id, Usuario u) {

    }

    public static boolean buscar(Connection con, String id) throws SQLException {
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            resultado = rs.next();
        } catch (SQLException e) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        if (resultado = true) {
            System.out.println("Usuario encontrado.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
        ps.close();
        ConnectionDB.closeConnection();
        return resultado;
    }

    public static int borrar(Connection con, String id) throws SQLException {

        int resultado = 0;
        PreparedStatement ps = null;
        if (buscar(con, id)) {
            String sql = "DELETE FROM usuarios WHERE id_usuario= ?";
            try {
                ps = con.prepareCall(sql);
                ps.setString(1, id);
                int valor = ps.executeUpdate();
                if (valor == 0) {
                    resultado = -1;
                    System.out.println("Error, no se puede eliminar al usuario.");
                } else {
                    resultado = 0;
                    System.out.println("Usuario eliminado correctamente.");
                }
            } catch (SQLException e) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        ps.close();
        ConnectionDB.closeConnection();
        return resultado;

    }

    public static HashSet<Usuario> devolverUsuarios(Connection con) {

        Set<Usuario> listaUsuarios = new HashSet<Usuario>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id_usuario, nombre, contraseña, id_rol FROM usuarios";
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(4) == 1) {
                    Usuario a = new Administrador(rs.getString(1), rs.getString(2), rs.getString(3));
                    listaUsuarios.add(a);
                } else {
                    Usuario a = new Profesor(rs.getString(1), rs.getString(2), rs.getString(3));
                    listaUsuarios.add(a);
                }

            }

        } catch (SQLException e) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
            listaUsuarios = null;
        }
        return (HashSet<Usuario>) listaUsuarios;
    }

}
