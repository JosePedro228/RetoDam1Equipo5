package bbdd;

import com.mycompany.historiashardware.Elemento;
import com.mycompany.historiashardware.Estado;
import com.mycompany.historiashardware.Ubicacion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM106
 */
public class InformeDAO {

    private static final String CONSULTASTOCK = "SELECT nombre, COUNT(*) FROM elementos GROUP BY nombre";

    public static void exportarCSVCompleto(Connection con, String rutaArchivo) {

        List<Elemento> inventarioCompleto = GestorAlmacenDAO.devolverInventarioCompleto(con);
        Map<String, Integer> mapaStock = new HashMap<>();
        File fichero = new File(rutaArchivo);
        try (Statement sentencia = con.createStatement()) {

            try (ResultSet rs = sentencia.executeQuery(CONSULTASTOCK)) {

                while (rs.next()) {
                    mapaStock.put(rs.getString(1), rs.getInt(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileWriter fr = new FileWriter(fichero); BufferedWriter bw = new BufferedWriter(fr)) {

            bw.write("Id;Categoria;Nombre;Descripcion;Estado;Ubicacion;Stock");
            bw.newLine();

            for (Elemento e : inventarioCompleto) {
                StringBuilder linea = new StringBuilder();
                linea.append(e.getId());
                linea.append(";");
                linea.append(e.getCategoria());
                linea.append(";");
                linea.append(e.getNombre());
                linea.append(";");
                linea.append(e.getDescripcion());
                linea.append(";");
                linea.append(e.getEstado());
                linea.append(";");
                linea.append(e.getUbicacion().getNombre());
                linea.append(";");
                linea.append(mapaStock.getOrDefault(e.getNombre(), 0));
                bw.write(linea.toString());
                bw.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void exportarCSVPorCategoria(Connection con, String rutaArchivo, String categoria) {
        List<Elemento> inventarioCompleto = GestorAlmacenDAO.listarInventarioTipo(con, categoria);
        Map<String, Integer> mapaStock = new HashMap<>();
        File fichero = new File(rutaArchivo);
        try (Statement sentencia = con.createStatement()) {

            try (ResultSet rs = sentencia.executeQuery(CONSULTASTOCK)) {

                while (rs.next()) {
                    mapaStock.put(rs.getString(1), rs.getInt(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileWriter fr = new FileWriter(fichero); BufferedWriter bw = new BufferedWriter(fr)) {

            bw.write("Id;Categoria;Nombre;Descripcion;Estado;Ubicacion;Stock");
            bw.newLine();

            for (Elemento e : inventarioCompleto) {
                StringBuilder linea = new StringBuilder();
                linea.append(e.getId());
                linea.append(";");
                linea.append(e.getCategoria());
                linea.append(";");
                linea.append(e.getNombre());
                linea.append(";");
                linea.append(e.getDescripcion());
                linea.append(";");
                linea.append(e.getEstado());
                linea.append(";");
                linea.append(e.getUbicacion().getNombre());
                linea.append(";");
                linea.append(mapaStock.getOrDefault(e.getNombre(), 0));
                bw.write(linea.toString());
                bw.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void exportarCSVPorEstado(Connection con, String rutaArchivo, String estado) {
        List<Elemento> inventarioEstado = GestorAlmacenDAO.listarInventarioEstado(con, estado);
        Map<String, Integer> mapaStock = new HashMap<>();
        File fichero = new File(rutaArchivo);
        try (Statement sentencia = con.createStatement()) {

            try (ResultSet rs = sentencia.executeQuery(CONSULTASTOCK)) {

                while (rs.next()) {
                    mapaStock.put(rs.getString(1), rs.getInt(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileWriter fr = new FileWriter(fichero); BufferedWriter bw = new BufferedWriter(fr)) {

            bw.write("Id;Categoria;Nombre;Descripcion;Estado;Ubicacion;Stock");
            bw.newLine();

            for (Elemento e : inventarioEstado) {
                StringBuilder linea = new StringBuilder();
                linea.append(e.getId());
                linea.append(";");
                linea.append(e.getCategoria());
                linea.append(";");
                linea.append(e.getNombre());
                linea.append(";");
                linea.append(e.getDescripcion());
                linea.append(";");
                linea.append(e.getEstado());
                linea.append(";");
                linea.append(e.getUbicacion().getNombre());
                linea.append(";");
                linea.append(mapaStock.getOrDefault(e.getNombre(), 0));
                bw.write(linea.toString());
                bw.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void exportarCSVPorLocalizacion(Connection con, String ruta, String ubicacion) {
        ArrayList<Elemento> inventarioLocalizacion = new ArrayList<>();
        Map<String, Integer> mapaStock = new HashMap<>();
        File fichero = new File(ruta);

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
            ps.setString(1, ubicacion); 
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    inventarioLocalizacion.add(new Elemento(
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
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, "Error al listar inventario por localización", ex);
        }

        try (Statement sentencia = con.createStatement(); ResultSet rsStock = sentencia.executeQuery(CONSULTASTOCK)) {
            while (rsStock.next()) {
                mapaStock.put(rsStock.getString(1), rsStock.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, "Error al procesar el stock", ex);
        }

        try (FileWriter fr = new FileWriter(fichero); BufferedWriter bw = new BufferedWriter(fr)) {
            bw.write("Id;Categoria;Nombre;Descripcion;Estado;Ubicacion;Stock");
            bw.newLine();
            for (Elemento e : inventarioLocalizacion) {
                StringBuilder linea = new StringBuilder();
                linea.append(e.getId()).append(";")
                        .append(e.getCategoria()).append(";")
                        .append(e.getNombre()).append(";")
                        .append(e.getDescripcion()).append(";")
                        .append(e.getEstado()).append(";")
                        .append(e.getUbicacion().getNombre()).append(";")
                        .append(mapaStock.getOrDefault(e.getNombre(), 0));
                bw.write(linea.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, "Error al escribir CSV", ex);
        }
    }
}
