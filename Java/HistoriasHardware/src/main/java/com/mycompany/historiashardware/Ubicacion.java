/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.historiashardware;

/**
 *
 * @author DAM106
 */
public class Ubicacion {
    
    private int id_ubicacion;
    
    private String tipo;
    
    private Ubicacion donde_esta;

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public Ubicacion getDonde_esta() {
        return donde_esta;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDonde_esta(Ubicacion donde_esta) {
        this.donde_esta = donde_esta;
    }
    
    
    
}
