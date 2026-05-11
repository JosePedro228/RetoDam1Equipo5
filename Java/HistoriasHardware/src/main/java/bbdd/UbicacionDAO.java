/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import com.mycompany.historiashardware.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            sentencia.setNull(2, java.sql.Types.INTEGER); //esto lo pone como nulo
        } else {
            sentencia.setInt(2, dondeEsta);
        }

        int FilasAfectadas = sentencia.executeUpdate();

        sentencia.close();
        ConnectionDB.closeConnection();

        return FilasAfectadas;
    }

    public static List<Ubicacion> mostrarUbicaciones(Connection con, int idUbicacion) {

        String sql = "SELECT * FROM ubicacion WHERE id_ubicacion = ? ";
        List<Ubicacion> listaUbicaciones = new ArrayList<>();
        try (PreparedStatement sentencia = con.prepareStatement(sql)) {
            sentencia.setInt(1, idUbicacion);
            try (ResultSet rs = sentencia.executeQuery()) {
                while (rs.next()) {
                    Ubicacion u = new Ubicacion();
                    u.setId_ubicacion(rs.getInt("id_ubicacion"));
                    u.setTipo(rs.getString("tipo"));
                    u.setDonde_esta(rs.getInt("donde_esta"));
                    listaUbicaciones.add(u);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUbicaciones;
    }

    public static int borrarUbicaciones(Connection con, int idUbicacion) {
        String sql = "DELETE FROM ubicacion WHERE id_ubicacion = ?";
        int filasAfectadas = 0;
        try (PreparedStatement sentencia = con.prepareStatement(sql)) {
            sentencia.setInt(1, idUbicacion);

            filasAfectadas = sentencia.executeUpdate();

            

        } catch (SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    return filasAfectadas;
    }

}
