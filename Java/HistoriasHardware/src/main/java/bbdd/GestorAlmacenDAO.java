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

/**
 *
 * @author DAM113
 */
public class GestorAlmacenDAO {
        public static List<Elemento> devolverInventarioCompleto(Connection con){
            List<Elemento> inventario = new ArrayList();
             try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_elemento,nombre_categoria AS categoria,"
                    + "nombre,descripcion,estado,ubicacion.id_ubicacion,tipo,donde_esta"
                    + " FROM elementos inner join categoria on  categoria.id_categoria= elementos.id_categoria inner join ubicacion on ubicacion.id_ubicacion=elementos.id_ubicacion");
            while (rs.next()) {
                inventario.add(new Elemento(rs.getInt("id_elemento"), rs.getString("nombre"),rs.getString("descripcion"),rs.getString("categoria"),
                        Estado.valueOf(rs.getString("estado").toUpperCase()),new Ubicacion(rs.getInt("id_ubicacion"),rs.getString("tipo"),rs.get)));
                System.out.println(rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }
             return inventario;
    }
}
