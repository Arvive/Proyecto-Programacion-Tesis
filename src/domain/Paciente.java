/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Usuario
 */
public class Paciente extends Usuario {

    public Paciente(String id, String nombreCompleto, String apellidos, String celular, String correo, String contraseña) {
        super(id, nombreCompleto, apellidos, celular, correo, contraseña);
    }

    @Override
    public String toString() {
        
        return getId() + ";" + getNombreCompleto() + ";" + getApellidos() + ";" +
               getCelular() + ";" + getCorreo() + ";" + getContraseña();
    }
}
    
