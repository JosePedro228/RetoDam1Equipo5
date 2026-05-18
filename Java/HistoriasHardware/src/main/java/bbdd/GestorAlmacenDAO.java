/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import Usuarios.Profesor;
import Usuarios.Usuario;
import static bbdd.UsuariosDAO.buscar;
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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author DAM113
 */
public class GestorAlmacenDAO {

    public static final String BASECONSULTA = "SELECT id_elemento,nombre_categoria AS categoria,"
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

    public static int Prestamo(Elemento ele, Usuario u, Connection con) throws SQLException {
        int resultado = -1;
        PreparedStatement ps = null;
        try {
            if (UsuariosDAO.buscar(con, u.getId_usuario()) && ElementoDAO.BuscarElemento(ele.getId(), con) != null) {
                String sql = "INSERT INTO prestamos (id_usuario, fecha, id_elemento, devuelto) VALUES (?,now(),?,false)";

                ps = con.prepareStatement(sql);

                ps.setString(1, u.getId_usuario());
                ps.setInt(2, ele.getId());

                int valor = ps.executeUpdate();
                if (valor == 0) {
                    resultado = -1;
                    System.out.println("Insertado");
                } else {
                    resultado = 0;
                    System.out.println("No insertado");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        if (ps != null) {
            ps.close();
        }

        return -2;

    }

    public static ArrayList<Elemento> listarInvetarioLocalizacion(Connection con, String nombre) {

        //variables
        ArrayList<Elemento> resultado = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String cadena = BASECONSULTA + " WHERE ubicacion.nombre = ?";

        try {

            //preparar consulta
            ps = con.prepareStatement(cadena);
            ps.setString(1, nombre);

            //ejecutar consulta
            rs = ps.executeQuery();

            while (rs.next()) {

                resultado.add(new Elemento(rs.getInt("id_elemento"), rs.getString("eleNombre"), rs.getString("descripcion"), rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()), new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))));

            }

            for (Elemento elemento : resultado) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public static ArrayList<Elemento> listarInventarioLocalizacionRecursive(Connection con, String nombre) {
        ArrayList<Elemento> resultado = new ArrayList<>();

        String sql
                = "WITH RECURSIVE arbol AS ("
                + "  SELECT id_ubicacion FROM ubicacion WHERE nombre = ? "
                + "  UNION ALL "
                + "  SELECT u.id_ubicacion FROM ubicacion u "
                + "  JOIN arbol a ON u.donde_esta = a.id_ubicacion "
                + ") "
                + "SELECT e.id_elemento, c.nombre_categoria AS categoria, "
                + "       e.nombre AS eleNombre, e.descripcion, e.estado, "
                + "       ub.id_ubicacion, ub.nombre AS ubNombre, ub.tipo, ub.donde_esta "
                + "FROM elementos e "
                + "JOIN categoria c  ON c.id_categoria  = e.id_categoria "
                + "JOIN ubicacion ub ON ub.id_ubicacion = e.id_ubicacion "
                + "WHERE e.id_ubicacion IN (SELECT id_ubicacion FROM arbol)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new Elemento(
                            rs.getInt("id_elemento"),
                            rs.getString("eleNombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            Estado.valueOf(rs.getString("estado").toUpperCase()),
                            new Ubicacion(rs.getInt("id_ubicacion"), rs.getString("ubNombre"), rs.getString("tipo"), rs.getString("donde_esta"))
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorAlmacenDAO.class.getName()).log(Level.SEVERE, "Error al listar inventario jerárquico", ex);
        }

        return resultado;
    }

    public static int CantPorNombre(List<Elemento> inventario, String nombre) {
        int cant = 0;
        for (Elemento e : inventario) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                cant++;
            }
        }
        return cant;
    }

}
