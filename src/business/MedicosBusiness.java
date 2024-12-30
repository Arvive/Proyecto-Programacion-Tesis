/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import data.MedicoData;
import domain.Medico;
import java.util.ArrayList;
import java.util.List;

public class MedicosBusiness {

    private MedicoData medicoData;

    public MedicosBusiness() {
        medicoData = new MedicoData();
    }

    public boolean registrarMedico(Medico medico) {
        return medicoData.registrarMedico(medico);
    }

    public List<Medico> obtenerMedicos() {
        return medicoData.obtenerMedicos();
    }
    
    public List<Medico> buscarMedicosPorNombre(String nombre) {
    List<Medico> resultados = new ArrayList<>();
    for (Medico medico : obtenerMedicos()) {
        if (medico.getNombreCompleto().toLowerCase().contains(nombre.toLowerCase())) {
            resultados.add(medico);
        }
    }
    return resultados;
}
    public List<Medico> buscarMedicosPorEspecialidad(String especialidad) {
        List<Medico> resultados = new ArrayList<>();
        for (Medico medico : obtenerMedicos()) {
            if (medico.getEspecialidad().toLowerCase().contains(especialidad.toLowerCase())) {
                resultados.add(medico);
            }
        }
        return resultados;
    }
    
    
    
    
}
    

    