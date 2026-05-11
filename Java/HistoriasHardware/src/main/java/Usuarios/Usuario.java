/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author DAM116
 */
public class Usuario {
    
    protected String nombre;
    protected String apellidos;
    protected String contrasenia;
    protected String id_usuario;

    public Usuario(String nombre, String apellidos, String contrasenia, String id_usuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasenia = contrasenia;
        this.id_usuario=id_usuario;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return  "nombre= " + nombre + "\n apellidos= " + apellidos + "\ncontrasenia= " + contrasenia + "\nid_usuario= " + id_usuario;
    }
    
    
    
}
