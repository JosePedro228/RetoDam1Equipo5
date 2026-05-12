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

            String s = "INSERT INTO elementos(NOMBRE, DESCRIPCION, ESTADO, ID_UBICACION, ID_CATEGORIA)"
                    + "VALUES (?,?,?,?,(Select id_categoria from categoria where nombre_categoria=?))";

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

        String s = "UPDATE elementos SET ESTADO = ? WHERE ID_ELEMENTO = ?";

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

        String s = "DELETE FROM elementos WHERE ID_ELEMENTO = ?";

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
   
   
}
