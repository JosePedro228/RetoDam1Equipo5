/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.*;

/**
 *
 * @author DAM113
 */
public class ConnectionDB {

    private static Connection conn;
    private static final String url = "jdbc:mysql://98.90.166.154:3306/mi_basedatos";
    private static final String usuario = "root";
    private static final String password = "mysql";
    
    
        public Connection openConnection () {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            // Class.forName("org.mariadb.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url,usuario, password);
        } catch (SQLException e) {
            System.out.println ("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println ("Exception: " + cE.toString());
        }
        
        return conn;
    }
}
