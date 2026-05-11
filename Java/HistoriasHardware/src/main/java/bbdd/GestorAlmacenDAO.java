/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

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
        public static ArrayList<String> devolverInventarioCompleto(Connection con){
    
             try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_elemento,(select nombre_categoria from categoria where categoria.id_categoria= elementos.id_categoria),"
                    + "nombre,descripcion,estado,id_ubicacion FROM elementos");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }
    }
}
