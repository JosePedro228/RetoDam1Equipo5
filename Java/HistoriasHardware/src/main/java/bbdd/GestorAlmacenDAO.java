/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import com.mycompany.historiashardware.Elemento;
import com.mycompany.historiashardware.Estado;
import com.mycompany.historiashardware.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;

/**
 *
 * @author DAM113
 */
public class GestorAlmacenDAO {

    private static final String BASECONSULTA = "SELECT id_elemento,nombre_categoria AS categoria,"
            + "elementos.nombre as eleNombre,descripcion,estado,ubicacion.id_ubicacion,ubicacion.nombre as ubNombre,tipo,donde_esta"
            + " FROM elementos inner join categoria on  categoria.id_categoria= elementos.id_categoria inner join ubicacion on ubicacion.id_ubicacion=elementos.id_ubicacion";

    public static List<Elemento> devolverInventarioCompleto(Connection con) {
        List<Elemento> inventario = new ArrayList();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(BASECONSULTA);
            while (rs.next()) {
                inventario.add(new Elemento(rs.getInt("id_elemento"), rs.getString("eleNombre"), rs.getString("descripcion"), rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()), new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))));

            }
        } catch (SQLException e) {
        }
        return inventario;
    }

    public static ArrayList<Elemento> listarInventarioNombre(Connection con, String nom) {
        //variables
        ArrayList<Elemento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = BASECONSULTA + " WHERE elementos.NOMBRE =?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, nom);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                resultado.add(new Elemento(rs.getInt("id_elemento"), rs.getString("eleNombre"), rs.getString("descripcion"), rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()), new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public static ArrayList<Elemento> listarInventarioTipo(Connection con, String t) {

        //variables
        ArrayList<Elemento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = BASECONSULTA + " WHERE nombre_categoria =?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, t);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                resultado.add(new Elemento(rs.getInt("id_elemento"), rs.getString("eleNombre"), rs.getString("descripcion"), rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()), new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public static ArrayList<Elemento> listarInventarioEstado(Connection con, String s) {

        //variables
        ArrayList<Elemento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = BASECONSULTA + " WHERE ESTADO =?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, s);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                resultado.add(new Elemento(rs.getInt("id_elemento"), rs.getString("eleNombre"), rs.getString("descripcion"), rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()), new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public static ArrayList<Elemento> listarInvetarioUbicacion(Connection con, int ubicacion) {

        //variables
        ArrayList<Elemento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = BASECONSULTA + " WHERE ubicacion.id_ubicacion = ?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setInt(1, ubicacion);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                resultado.add(new Elemento(rs.getInt("id_elemento"), rs.getString("eleNombre"), rs.getString("descripcion"), rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()), new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

}
