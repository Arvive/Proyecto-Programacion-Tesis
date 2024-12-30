/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import data.CitaData;
import data.MedicoData;
import data.PacienteData;
import domain.Cita;
import domain.Medico;
import domain.Paciente;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitaBusiness {

    private CitaData citaData;
    private MedicoData medicoData;
    private PacienteData pacienteData;

    public CitaBusiness(CitaData citaData, MedicoData medicoData, PacienteData pacienteData) {
        this.citaData = citaData;
        this.medicoData = medicoData;
        this.pacienteData = pacienteData;
    }

    public boolean registrarCita(Cita cita) {
        List<Medico> medicos = medicoData.obtenerMedicos();
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        List<Cita> citas = citaData.obtenerCitas(medicos, pacientes);
        // Verifica si el paciente y el médico existen
        if (cita.getMedico() == null) {
            throw new RuntimeException("El medico no existe.");
        }

        if (cita.getPaciente() == null) {
            throw new RuntimeException("El paciente no existe.");
        }
        if (medicos.contains(cita.getMedico())) {
            throw new RuntimeException("El medico no existe2.");
        }
        if (pacientes.contains(cita.getPaciente())) {
            throw new RuntimeException("El paciente no existe2.");
        }

        // Verifica el horario de la cita
        for (Cita c : citas) {
            if (c.getMedico().getMedicoId().equals(cita.getMedico().getMedicoId()) && c.getFecha().equals(cita.getFecha())) {
                // Verifica si la nueva cita se solapa con la existente
                LocalTime horaFinCitaExistente = c.getHora().plusHours(1);
                // La nueva cita debe empezar al menos una hora después de la cita existente
                if (cita.getHora().isBefore(horaFinCitaExistente) && !cita.getHora().plusHours(1).isBefore(c.getHora())) {
                    throw new RuntimeException("La hora de la cita debe ser al menos una hora después de la cita anterior.");
                }
            }
        }

        // Registrar la nueva cita
        return citaData.registrarCita(cita);
    }

    public boolean actualizarCita(Cita cita) {
        List<Medico> medicos = medicoData.obtenerMedicos();
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        List<Cita> citas = citaData.obtenerCitas(medicos, pacientes);

        System.out.println("llega a actualizar" +cita.getMedico());
        if (cita.getMedico() == null) {
          System.out.println("llega a actualizar2" +medicos.contains(cita.getMedico()));
            throw new RuntimeException("El médico no está registrado.");
        }
        
        if (cita.getPaciente() == null) {
            
            throw new RuntimeException("El paciente no está registrado.");
        }

        // Verifica el horario de la cita, NO FUNCIONA LA VALIDACION
        for (Cita c : citas) {
            if (c.getMedico().equals(cita.getMedico()) && c.getFecha().equals(cita.getFecha())) {
                // Verifica si la nueva cita se solapa con la existente
                LocalTime horaFinCitaExistente = c.getHora().plusHours(1);
                if (cita.getHora().isBefore(horaFinCitaExistente) && !cita.getHora().plusHours(1).isBefore(c.getHora())) {
                    throw new RuntimeException("La hora de la cita debe ser al menos una hora después de la cita anterior.");
                }
            }
        }

    // Actualizar la cita
    return citaData.actualizarCita(cita,medicos,pacientes);
}

    public boolean eliminarCita(String idCita) throws IOException {
        List<Medico> medicos = medicoData.obtenerMedicos();
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        return citaData.eliminarCita(idCita, medicos, pacientes);
    }

    public List<Cita> obtenerCitas() {
        List<Medico> medicos = medicoData.obtenerMedicos();
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        return citaData.obtenerCitas(medicos, pacientes);
    }

    public List<Cita> obtenerCitasPorPaciente(String idPaciente) {
        // Obtiene la lista de pacientes desde la capa de datos
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        // Encuentra el paciente con el nombre dado
        Paciente paciente = pacientes.stream()
                .filter(p -> p.getId().equals(idPaciente))
                .findFirst()
                .orElse(null);
        System.out.println("paciente:" + pacientes);
        if (paciente != null) {
            System.out.println("citasdata:" + citaData.obtenerCitas(medicoData.obtenerMedicos(), pacientes));
            // Obtiene todas las citas y filtra por el paciente encontrado
            return citaData.obtenerCitas(medicoData.obtenerMedicos(), pacienteData.obtenerPacientes())
                    .stream()
                    .filter(c -> c.getPaciente().getId().equals(paciente.getId()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public Cita obtenerCitaPorId(String idCita) {
        List<Medico> medicos = medicoData.obtenerMedicos();
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        return citaData.obtenerCitaPorId(idCita, medicos, pacientes);
    }
}
