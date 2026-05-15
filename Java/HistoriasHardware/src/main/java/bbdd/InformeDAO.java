package bbdd;

import com.mycompany.historiashardware.Elemento;
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
                    mapaStock.put(rs.getString(1),rs.getInt(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileWriter fr = new FileWriter(fichero); BufferedWriter bw = new BufferedWriter(fr)) {

            bw.write("Id,Categoria,Nombre,Descripcion,Estado,Ubicacion,Stock");
            bw.newLine();

            for (Elemento e : inventarioCompleto) {
                StringBuilder linea = new StringBuilder();
                linea.append(e.getId());
                linea.append(",");
                linea.append(e.getCategoria());
                linea.append(",");
                linea.append(e.getNombre());
                linea.append(",");
                linea.append(e.getDescripcion());
                linea.append(",");
                linea.append(e.getEstado());
                linea.append(",");
                linea.append(e.getUbicacion().getNombre());
                linea.append(",");
                linea.append(mapaStock.getOrDefault(e.getNombre(),0));
                bw.write(linea.toString());
                bw.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void exportarCSVPorCategoria(Connection con, String rutaArchivo, String categoria){
        List<Elemento> inventarioCompleto = GestorAlmacenDAO.listarInventarioTipo(con,categoria);
        Map<String, Integer> mapaStock = new HashMap<>();
        File fichero = new File(rutaArchivo);
        try (Statement sentencia = con.createStatement()) {

            try (ResultSet rs = sentencia.executeQuery(CONSULTASTOCK)) {

                while (rs.next()) {
                    mapaStock.put(rs.getString(1),rs.getInt(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileWriter fr = new FileWriter(fichero); BufferedWriter bw = new BufferedWriter(fr)) {

            bw.write("Id,Categoria,Nombre,Descripcion,Estado,Ubicacion,Stock");
            bw.newLine();

            for (Elemento e : inventarioCompleto) {
                StringBuilder linea = new StringBuilder();
                linea.append(e.getId());
                linea.append(",");
                linea.append(e.getCategoria());
                linea.append(",");
                linea.append(e.getNombre());
                linea.append(",");
                linea.append(e.getDescripcion());
                linea.append(",");
                linea.append(e.getEstado());
                linea.append(",");
                linea.append(e.getUbicacion().getNombre());
                linea.append(",");
                linea.append(mapaStock.getOrDefault(e.getNombre(),0));
                bw.write(linea.toString());
                bw.newLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(InformeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
    
    }
}
