/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import domain.Cita;
import domain.Medico;
import domain.Paciente;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CitaData {

    private final String archivo = "citas.txt";

    public boolean registrarCita(Cita cita) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(cita.toString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cita> obtenerCitas(List<Medico> medicos, List<Paciente> pacientes) {
    List<Cita> citas = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println("Leyendo línea: " + linea); // Imprimir la línea leída para depuración
            String[] datos = linea.split(";");
            if (datos.length == 5) {
                String citaId = datos[0];
                LocalDate fecha = LocalDate.parse(datos[1]);
                LocalTime hora = LocalTime.parse(datos[2]);
                Medico medico = medicos.stream().filter(m -> m.getMedicoId().equals(datos[3])).findFirst().orElse(null);
                Paciente paciente = pacientes.stream().filter(p -> p.getId().equals(datos[4])).findFirst().orElse(null);
                if (medico != null && paciente != null) {
                    Cita cita = new Cita(citaId, fecha, hora, medico, paciente);
                    citas.add(cita);
                } else {
                    System.err.println("Cita con ID " + citaId + " tiene datos de médico o paciente inválidos.");
                }
            } else {
                System.err.println("Formato de línea inválido: " + linea);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return citas;
}

    public boolean actualizarCita(Cita citaModificada,  List<Medico> medicos, List<Paciente> pacientes) {
        List<Cita> citas = obtenerCitas(medicos, pacientes);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            boolean actualizado = false;
            for (Cita cita : citas) {
                if (cita.getCitaId().equals(citaModificada.getCitaId())) {
                    bw.write(citaModificada.toString());
                    actualizado = true;
                } else {
                    bw.write(cita.toString());
                }
                bw.newLine();
            }
            return actualizado;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCita(String idCita, List<Medico> medicos, List<Paciente> pacientes) {
        List<Cita> citas = obtenerCitas(medicos, pacientes);
        boolean eliminado = false;

        // Archivo temporal para almacenar las citas restantes.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tempFile.txt"))) {
            for (Cita cita : citas) {
                if (!cita.getCitaId().equals(idCita)) {
                    bw.write(cita.toString()); 
                    bw.newLine();
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Reemplazar el archivo original por el archivo temporal
        if (eliminado) {
            try (BufferedReader br = new BufferedReader(new FileReader("tempFile.txt")); BufferedWriter bwOriginal = new BufferedWriter(new FileWriter(archivo))) {

                String line;
                while ((line = br.readLine()) != null) {
                    bwOriginal.write(line);
                    bwOriginal.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Eliminar archivo temporal
        File tempFile = new File("tempFile.txt");
        if (!tempFile.delete()) {
            System.err.println("No se pudo eliminar el archivo temporal.");
        }

        return eliminado;
    }

    public Cita obtenerCitaPorId(String idCita, List<Medico> medicos, List<Paciente> pacientes) {
        List<Cita> citas = obtenerCitas(medicos, pacientes);
        return citas.stream().filter(c -> c.getCitaId().equals(idCita)).findFirst().orElse(null);
    }
}
