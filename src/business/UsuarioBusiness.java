/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
     *
     * @param cedula
     * @param contrasena
     * @return
     */
package business;

import data.UsuarioData;
import domain.Usuario;
import java.util.List;

public class UsuarioBusiness {

    private UsuarioData usuarioData;

    public UsuarioBusiness() {
        usuarioData = new UsuarioData();
    }

    public boolean registrarUsuario(Usuario usuario) {
        return usuarioData.registrarUsuario(usuario);
    }

    public Usuario obtenerUsuarioPorId(String id) {
        return usuarioData.obtenerUsuarioPorId(id);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioData.obtenerUsuarios();
    }

    public Usuario autenticarUsuario(String cedula, String contrasena) {
    Usuario usuario = usuarioData.obtenerUsuarioPorCedulaYContrasena(cedula, contrasena);
    if (usuario != null) {
        SesionBusiness.getInstancia().setUsuarioAutenticado(usuario);
    }
    return usuario;
}
}