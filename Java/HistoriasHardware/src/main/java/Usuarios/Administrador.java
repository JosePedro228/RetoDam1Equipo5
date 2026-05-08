/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author DAM116
 */
public class Administrador extends Usuario{
    
    public Administrador(String nombre, String apellidos, String id_usuario, String contrasenia) {
        super(nombre, apellidos, id_usuario, contrasenia);
    }

    @Override
    public String toString() {
        return "Administrador:" +super.toString();
    }
    
   
    
}
