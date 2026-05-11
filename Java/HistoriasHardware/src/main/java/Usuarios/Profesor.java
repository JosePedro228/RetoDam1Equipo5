/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author DAM116
 */
public class Profesor extends Usuario{
    private int id_rol;
    public Profesor(String nombre, String id_usuario, String contrasenia, int id_rol) {
        super(nombre, id_usuario, contrasenia);
        this.id_rol=id_rol;
    }

    @Override
    public String toString() {
        return "Profesor: " +super.toString();
    }
    
    
    
}
