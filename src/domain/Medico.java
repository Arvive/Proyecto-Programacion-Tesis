/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Medico {
    
    private String medicoId;  
    private String nombreCompleto;
    private String especialidad;
    private String ubicacion;

    public Medico(String medicoId, String nombreCompleto, String especialidad, String ubicacion) {
        this.medicoId = medicoId;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.ubicacion = ubicacion;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return medicoId + ";" + nombreCompleto + ";" + especialidad + ";" + ubicacion;
    }
    
}
    

