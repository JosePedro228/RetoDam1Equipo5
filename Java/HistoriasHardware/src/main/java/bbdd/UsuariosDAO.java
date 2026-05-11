/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

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

    public static void insertar(Connection con, Usuario u) {
        int resultado = -1;
        PreparedStatement ps = null;
        if(u!=null && !buscar(con, u)){
            String sql ="INSERT INTO usuarios (id_usuario, nombre, contraseña, id_rol) VALUES (?,?,?,?)";
            try{
                ps=con.prepareStatement(sql);
                ps.setString(1, u.getId_usuario());
                ps.setString(2, u.getNombre());
                ps.setString(3, u.getContrasenia());
                if(u instanceof Profesor){
                    ps.setString(4, );
                }else{
                    
                }
                
                
            }catch(SQLException e){
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    public static void modificar() {

    }

    public static boolean buscar(Connection con, Usuario u) {
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getId_usuario());
            rs=ps.executeQuery();
            resultado=rs.next();
        } catch (SQLException e) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public void borrar() {

    }

}
