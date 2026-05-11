/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import com.mycompany.historiashardware.Elemento;
import com.mycompany.historiashardware.Ubicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM112
 */
public class ElementoDAO {

    public static int insertarElemento(Connection con, Elemento elemento) {

        int resultado = -1;
        PreparedStatement ps = null;

        if (elemento != null) {

            String s = "INSERT INTO ELEMENTOS(NOMBRE, DESCRIPCION, ESTADO, ID_UBICACION, ID_CATEGORIA)"
                    + "VALUES (?,?,?,?,(Select id_categoria from Categoria where nombre_categoria=?))";

            try {

                //crear consulta con el formato de la cadena
                ps = con.prepareStatement(s);

                ps.setString(1, elemento.getNombre());
                ps.setString(2, elemento.getDescripcion());
                ps.setString(3, elemento.getEstado().toString());
                ps.setInt(4, elemento.getUbicacion().getId_ubicacion());
                ps.setString(5, elemento.getCategoria());

                //ejecutar query
                resultado = ps.executeUpdate();

                //si falla el metodo devuelve cero -> resultado = -1
                if (resultado == 0) {

                    resultado = -1;

                } else {//si no falla entonces resultado = 0

                    resultado = 0;
                }

            } catch (SQLException ex) {

                Logger.getLogger(ElementoDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }

    //REVISAR
    public static int actualizarEstadoElemento(Connection con, int id_elemento, String estado) {

        int resultado = -1;
        PreparedStatement ps = null;

        String s = "UPDATE ELEMENTOS SET ESTADO = ? WHERE ID_ELEMENTO = ?";

        try {

            ps = con.prepareStatement(s);

            ps.setString(1, estado);
            ps.setInt(2, id_elemento);

            resultado = ps.executeUpdate();

            if (resultado == 0) {

                resultado = -1;
            } else {

                resultado = 0;
            }

        } catch (SQLException ex) {

            Logger.getLogger(ElementoDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    public static int eliminarElemento(Connection con, int id){
    
        int resultado = -1;
        PreparedStatement ps = null;

        String s = "DELETE FROM ELEMENTOS WHERE ID_ELEMENTO = ?";

        try {

            ps = con.prepareStatement(s);

            ps.setInt(1, id);

            resultado = ps.executeUpdate();

            if (resultado == 0) {

                resultado = -1;
            } else {

                resultado = 0;
            }

        } catch (SQLException ex) {

            Logger.getLogger(ElementoDAO.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    //REVISAR METODO -> GUARDAR UBICACIONES, ID 
    public static ArrayList<String> listarInventario(Connection con) {

        //variables
        ArrayList<String> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = "SELECT ID, NOMBRE, DESCRIPCION, TIPO, ESTADO, UBICACION"
                + "FROM ELEMENTOS";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String desc = rs.getString(3);
                String tipo = rs.getString(4);
                String estado = rs.getString(5);
                int ubi = rs.getInt(6);

                //añadir resultados a la lista
                resultado.add(id + " " + nombre + " " + desc + " " + tipo + " " + estado + " " + ubi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;

    }

    public static ArrayList<String> listarInventarioNombre(Connection con, String nom) {
        //variables
        ArrayList<String> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = "SELECT ID, NOMBRE, DESCRIPCION, TIPO, ESTADO, UBICACION"
                + "FROM ELEMENTOS WHERE NOMBRE = ?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, nom);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String desc = rs.getString(3);
                String tipo = rs.getString(4);
                String estado = rs.getString(5);
                int ubi = rs.getInt(6);

                //añadir resultados a la lista
                resultado.add(id + " " + nombre + " " + desc + " " + tipo + " " + estado + " " + ubi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    public static ArrayList<String> listarInventarioTipo(Connection con, String t){
    
         //variables
        ArrayList<String> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = "SELECT ID, NOMBRE, DESCRIPCION, TIPO, ESTADO, UBICACION"
                + "FROM ELEMENTOS WHERE TIPO = ?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, t);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String desc = rs.getString(3);
                String tipo = rs.getString(4);
                String estado = rs.getString(5);
                int ubi = rs.getInt(6);

                //añadir resultados a la lista
                resultado.add(id + " " + nombre + " " + desc + " " + tipo + " " + estado + " " + ubi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    public static ArrayList<String> listarInventarioEstado(Connection con, String s){
    
         //variables
        ArrayList<String> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = "SELECT ID, NOMBRE, DESCRIPCION, TIPO, ESTADO, UBICACION"
                + "FROM ELEMENTOS WHERE ESTADO = ?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, s);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String desc = rs.getString(3);
                String tipo = rs.getString(4);
                String estado = rs.getString(5);
                int ubi = rs.getInt(6);

                //añadir resultados a la lista
                resultado.add(id + " " + nombre + " " + desc + " " + tipo + " " + estado + " " + ubi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
    public static ArrayList<String> listarInvetarioUbicacion(Connection con, int ubicacion){
    
         //variables
        ArrayList<String> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = "SELECT ID, NOMBRE, DESCRIPCION, TIPO, ESTADO, UBICACION"
                + "FROM ELEMENTOS WHERE UBICACION = ?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setInt(1, ubicacion);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String desc = rs.getString(3);
                String tipo = rs.getString(4);
                String estado = rs.getString(5);
                int ubi = rs.getInt(6);

                //añadir resultados a la lista
                resultado.add(id + " " + nombre + " " + desc + " " + tipo + " " + estado + " " + ubi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

}
