/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import domain.Paciente;
import domain.Usuario;

public class SesionBusiness {
    
    private static SesionBusiness instancia;
    private Usuario usuarioAutenticado;
    private Paciente pacienteAutenticado;
    private boolean usuarioAutenticadoFlag; // Flag para indicar si hay un usuario autenticado

    private SesionBusiness() {}

    public static SesionBusiness getInstancia() {
        if (instancia == null) {
            instancia = new SesionBusiness();
        }
        return instancia;
    }

    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
        if (usuario instanceof Paciente) {
            pacienteAutenticado = (Paciente) usuario;
        } else {
            pacienteAutenticado = null;
        }
        usuarioAutenticadoFlag = true; // Indica que hay un usuario autenticado
    }

    public void cerrarSesion() {
        usuarioAutenticado = null;
        pacienteAutenticado = null;
        usuarioAutenticadoFlag = false; // Indica que no hay un usuario autenticado
    }

    public boolean isUsuarioAutenticado() {
        return usuarioAutenticadoFlag;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
    
    public Paciente getPacienteAutenticado() {
        return pacienteAutenticado;
    }
}