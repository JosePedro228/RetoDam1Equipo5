/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import java.util.Objects;

/**
 *
 * @author DAM116
 */
public class Usuario {
    
    protected String nombre;
    
    protected String id_usuario;
    protected String contrasenia;
    

    public Usuario(String nombre, String id_usuario, String contrasenia) {
        this.nombre = nombre;
       
        this.id_usuario = id_usuario;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "\nnombre= " + nombre + "\nid_usuario= " + id_usuario + "\ncontrasenia= " + contrasenia;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null || getClass()!=obj.getClass()){
            return false;
        }
        Usuario u=(Usuario)obj;
        return this.id_usuario.equalsIgnoreCase(u.getId_usuario()) && this.contrasenia.equalsIgnoreCase(u.getContrasenia()) && this.nombre.equalsIgnoreCase(u.getNombre());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(this.id_usuario.toLowerCase(), this.contrasenia.toLowerCase(), this.nombre.toLowerCase());
    }
}
