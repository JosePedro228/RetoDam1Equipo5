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
    private int id_rol;
    
    public Administrador(String nombre, String id_usuario, String contrasenia, int id_rol) {
        super(nombre, id_usuario, contrasenia);
        this.id_rol=id_rol;
    }

    @Override
    public String toString() {
        return "Administrador:" +super.toString();
    }
    
   
    
}
