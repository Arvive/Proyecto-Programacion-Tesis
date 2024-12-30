/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import domain.Paciente;
import java.io.*;
import java.util.*;

public class PacienteData {

    private final String archivo = "usuarios.txt";

    public boolean registrarPaciente(Paciente paciente) {
        boolean exito = false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(paciente.toString());
            bw.newLine();
            exito = true; // Registro exitoso
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public List<Paciente> obtenerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    Paciente paciente = new Paciente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
}