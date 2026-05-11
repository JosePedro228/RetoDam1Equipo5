/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author DAM106
 */
public class UbicacionDAO {

    public static int insertar(Connection con) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        Integer dondeEsta = null;

        System.out.print("Introduce el tipo (armario/balda/caja):");
        String tipo = teclado.nextLine();

        if (tipo.equalsIgnoreCase("caja")) {
            System.out.println("En que balda quieres guardar la caja"); //Las cajas siempre estarán en baldas, no pueden estar sueltas
            int caja = Integer.parseInt(teclado.nextLine());
            dondeEsta = caja;
        } else if (tipo.equalsIgnoreCase("balda")) {
            System.out.println("¿Está dentro de un armario? (S/N)"); //Existen baldas sueltas que no están en los armarios
            String respuesta = teclado.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Introduce el ID del armario:");
                dondeEsta = Integer.parseInt(teclado.nextLine());
            }
            
        }

        

        String sql = "INSERT INTO ubicacion (tipo, donde_esta) VALUES (?, ?)";

        PreparedStatement sentencia = con.prepareStatement(sql);
        sentencia.setString(1, tipo);

        if (dondeEsta == null) {
            sentencia.setNull(2, java.sql.Types.INTEGER);
        } else {
            sentencia.setInt(2, dondeEsta);
        }

        int numFilasAfectadas = sentencia.executeUpdate();
        
        sentencia.close();
        ConnectionDB.closeConnection();
        
        return numFilasAfectadas;
    }
    
    
}
