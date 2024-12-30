/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import domain.Medico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoData {

    private static final String FILE_NAME = "medicos.txt";

    public MedicoData() {
    }

    public boolean registrarMedico(Medico medico) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(medico.toString());
            writer.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public List<Medico> obtenerMedicos() {
        List<Medico> medicos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] data = linea.split(";");
                String id = data[0];
                String nombreCompleto = data[1];
                String especialidad = data[2];
                String ubicacion = data[3];
                medicos.add(new Medico(id, nombreCompleto, especialidad, ubicacion));
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return medicos;
    }
}