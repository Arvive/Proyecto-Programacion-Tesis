/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import business.MedicosBusiness;
import domain.Medico;
import javax.swing.*;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BusquedaMedicosForm extends JFrame {

    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JTextArea txtResultados;
    private JComboBox<String> cboCriterio;

    private MedicosBusiness medicosBusiness;

    public BusquedaMedicosForm() {
        setTitle("Búsqueda de Médicos");
        setSize(590, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        medicosBusiness = new MedicosBusiness();

        JLabel lblBusqueda = new JLabel("Buscar médico:");
        lblBusqueda.setBounds(20, 20, 100, 25);
        add(lblBusqueda);

        txtBusqueda = new JTextField();
        txtBusqueda.setBounds(120, 20, 200, 25);
        add(txtBusqueda);
        
        cboCriterio = new JComboBox<>(new String[] {"Nombre", "Especialidad"});
        cboCriterio.setBounds(330, 20, 120, 25);
        add(cboCriterio);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(470, 20, 80, 25);
        add(btnBuscar);

        txtResultados = new JTextArea();
        txtResultados.setBounds(20, 60, 360, 400);
        txtResultados.setEditable(false);
        add(txtResultados);

      
        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarMedicos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarMedicos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            
            }
        });
    }
    
    

    private void buscarMedicos() {
        String criterio = (String) cboCriterio.getSelectedItem();
        String textoBusqueda = txtBusqueda.getText();
        List<Medico> medicos;

        if ("Nombre".equals(criterio)) {
            medicos = medicosBusiness.buscarMedicosPorNombre(textoBusqueda);
        } else { // Especialidad
            medicos = medicosBusiness.buscarMedicosPorEspecialidad(textoBusqueda);
        }

        if (!medicos.isEmpty()) {
            StringBuilder resultados = new StringBuilder();
            for (Medico medico : medicos) {
                resultados.append("ID: ").append(medico.getMedicoId()).append("\n")
                        .append("Nombre: ").append(medico.getNombreCompleto()).append("\n")
                        .append("Especialidad: ").append(medico.getEspecialidad()).append("\n")
                        .append("Ubicación: ").append(medico.getUbicacion()).append("\n\n");
            }
            txtResultados.setText(resultados.toString());
        } else {
            txtResultados.setText("No se encontraron médicos con ese criterio de búsqueda.");
        }
    }
}