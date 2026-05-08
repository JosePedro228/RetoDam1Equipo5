/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM113
 */
public class ConnectionDB {

    private static Connection conn;
    private static final String url = "jdbc:mysql://98.90.166.154:3306/Inventario";
    private static final String usuario = "admin";
    private static final String password = "1234";
    
    
        public static Connection openConnection () {
        try {
          
            conn = (Connection) DriverManager.getConnection(url,usuario, password);
        } catch (SQLException e) {
            System.out.println ("SQL Exception: " + e.toString());
        } 
        
        return conn;
    }
         public static  void closeConnection () {
        // Cerramos la conexión
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
