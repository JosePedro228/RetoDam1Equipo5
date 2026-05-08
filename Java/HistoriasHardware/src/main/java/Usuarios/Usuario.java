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
    protected String id_usuario;
    protected String contrasenia;

    public Usuario(String nombre, String apellidos, String id_usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_usuario = id_usuario;
        this.contrasenia = contrasenia;
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

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "\nnombre= " + nombre + "\napellidos= " + apellidos + "\nid_usuario= " + id_usuario + "\ncontrasenia= " + contrasenia;
    }
    
    
    
}
