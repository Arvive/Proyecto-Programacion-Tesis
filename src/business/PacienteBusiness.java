package business;

import data.PacienteData;
import domain.Paciente;
import java.util.List;

public class PacienteBusiness {
    
    private PacienteData pacienteData;

    public PacienteBusiness() {
        this.pacienteData = new PacienteData();
    }

    public boolean registrarPaciente(Paciente paciente) {
        return pacienteData.registrarPaciente(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteData.obtenerPacientes();
    }

    public Paciente obtenerPacientePorId(String id) {
        List<Paciente> pacientes = pacienteData.obtenerPacientes();
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    public Paciente obtenerPacienteActual() {
    SesionBusiness sesionBusiness = SesionBusiness.getInstancia();
    if (sesionBusiness.isUsuarioAutenticado() && sesionBusiness.getPacienteAutenticado() != null) {
        return sesionBusiness.getPacienteAutenticado();
    }
    return null; // O lanzar una excepción si prefieres manejarlo de otra manera
}
}