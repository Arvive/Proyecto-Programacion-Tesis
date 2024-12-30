/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import domain.Paciente;
import domain.Usuario;
import java.io.*;
import java.util.*;

public class UsuarioData {

    private static final String FILE_NAME = "usuarios.txt";

    public UsuarioData() {
    }

    public boolean registrarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(usuario.toString());
            writer.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Usuario obtenerUsuarioPorId(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] data = linea.split(";");
                if (data.length == 6) { // Asegúrate de que el número de campos coincide con los datos
                    String usuarioId = data[0];
                    if (usuarioId.equals(id)) {
                        String nombreCompleto = data[1];
                        String apellidos = data[2];
                        String celular = data[3];
                        String correo = data[4];
                        String contraseña = data[5];
                        return new Paciente(usuarioId, nombreCompleto, apellidos, celular, correo, contraseña);
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] data = linea.split(";");
                if (data.length == 6) { 
                    String id = data[0];
                    String nombreCompleto = data[1];
                    String apellidos = data[2];
                    String celular = data[3];
                    String correo = data[4];
                    String contraseña = data[5];
                    usuarios.add(new Paciente(id, nombreCompleto, apellidos, celular, correo, contraseña));
                }
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return usuarios;
    }

    public Usuario obtenerUsuarioPorCedulaYContrasena(String cedula, String contrasena) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] data = linea.split(";");
                if (data.length == 6) { 
                    String usuarioId = data[0];
                    String storedContrasena = data[5];

                    if (usuarioId.equals(cedula) && storedContrasena.equals(contrasena)) {
                        return new Paciente(usuarioId, data[1], data[2], data[3], data[4], contrasena);
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}