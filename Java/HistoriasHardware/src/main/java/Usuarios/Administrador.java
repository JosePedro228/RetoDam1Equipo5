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
    
    public Administrador(String id_usuario, String nombre, String contrasenia) {
        super(nombre, id_usuario, contrasenia);
    }

    @Override
    public String toString() {
        return "Administrador:" +super.toString();
    }
    
   
    
}
