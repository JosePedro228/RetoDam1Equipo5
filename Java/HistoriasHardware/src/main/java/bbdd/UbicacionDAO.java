/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DAM106
 */
public class UbicacionDAO {
    
    
    public int insertar() throws SQLException{
        
        Connection con = ConnectionDB.openConnection();
        Statement sentencia = con.createStatement();
        
        sentencia.execute(sql)
        
    }
}
