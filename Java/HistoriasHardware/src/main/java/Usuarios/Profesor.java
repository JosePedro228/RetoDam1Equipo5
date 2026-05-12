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

    public Profesor(String id_usuario, String nombre, String contrasenia) {
        super(id_usuario, nombre, contrasenia);
    }
    
    

    @Override
    public String toString() {
        return "Profesor: " +super.toString();
    }
    
    
    
}
