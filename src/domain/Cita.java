/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private String citaId;
    private LocalDate fecha;
    private LocalTime hora;
    private Medico medico;
    private Paciente paciente;

    public Cita(String citaId, LocalDate fecha, LocalTime hora, Medico medico, Paciente paciente) {
        this.citaId = citaId;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getCitaId() {
        return this.citaId;
    }

    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return citaId + ";" + fecha + ";" + hora + ";" + medico.getMedicoId() + ";" + paciente.getId();
    }
}