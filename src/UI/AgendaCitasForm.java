/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;


import business.CitaBusiness;
import business.MedicosBusiness;
import business.PacienteBusiness;
import business.SesionBusiness;
import domain.Cita;
import domain.Medico;
import domain.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import data.CitaData;
import data.MedicoData;
import data.PacienteData;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AgendaCitasForm extends JFrame implements ActionListener{

    private JComboBox<Medico> cboMedicos;
    private JButton btnProgramar;
    private JButton btnModificar;
    private JButton btnCancelar;
    private JDateChooser dateChooser;
    private JSpinner timeSpinner;
    private JTable tableCitas; // Añadido para mostrar las citas
    private DefaultTableModel tableModel; // Modelo para la tabla de citas
    private CitaBusiness citasBusiness;
    private MedicosBusiness medicosBusiness;
    private PacienteBusiness pacienteBusiness;
    private Paciente pacienteActual;

    public AgendaCitasForm() {
        if (!SesionBusiness.getInstancia().isUsuarioAutenticado()) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión para agendar una cita.");
            InicioSesionForm inicioSesionForm = new InicioSesionForm();// se abre incio de sesion
            inicioSesionForm.setVisible(true);
            
            dispose(); // Cierra la ventana si no hay usuario autenticado
            return;
        }

        pacienteActual = (Paciente) SesionBusiness.getInstancia().getUsuarioAutenticado();
        if (!(pacienteActual instanceof Paciente)) {
            JOptionPane.showMessageDialog(this, "No se ha encontrado el paciente actual. Por favor, asegúrate de que el usuario autenticado es un paciente.");
            dispose(); // Cierra la ventana si no se encuentra un paciente
            return;
        }

        CitaData citaData = new CitaData();
        MedicoData medicoData = new MedicoData();
        PacienteData pacienteData = new PacienteData();

        citasBusiness = new CitaBusiness(citaData, medicoData, pacienteData);
        medicosBusiness = new MedicosBusiness();
        pacienteBusiness = new PacienteBusiness();

        cargarComponentes();
        cargarDatosalSeleccionar();
        actualizarTablaCitas();

        
    }

    private void cargarComponentes() {
        setTitle("Agenda de Citas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(null);
        inputPanel.setPreferredSize(new Dimension(600, 200));

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(20, 20, 100, 25);
        inputPanel.add(lblFecha);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 20, 150, 25);
        inputPanel.add(dateChooser);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(20, 60, 100, 25);
        inputPanel.add(lblHora);

        SpinnerDateModel model = new SpinnerDateModel();
        timeSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(editor);
        timeSpinner.setBounds(120, 60, 150, 25);
        inputPanel.add(timeSpinner);

        JLabel lblMedico = new JLabel("Médico:");
        lblMedico.setBounds(20, 100, 100, 25);
        inputPanel.add(lblMedico);

        cboMedicos = new JComboBox<>(getMedicos());
        cboMedicos.setBounds(120, 100, 350, 25);
        inputPanel.add(cboMedicos);

        btnProgramar = new JButton("Programar Cita");
        btnProgramar.setBounds(20, 140, 150, 30);
        inputPanel.add(btnProgramar);

        btnModificar = new JButton("Modificar Cita");
        btnModificar.setBounds(180, 140, 150, 30);
        inputPanel.add(btnModificar);

        btnCancelar = new JButton("Cancelar Cita");
        btnCancelar.setBounds(340, 140, 150, 30);
        inputPanel.add(btnCancelar);

        add(inputPanel, BorderLayout.NORTH);
        
        btnProgramar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnCancelar.addActionListener(this);
        
        String [] columnNames = {"ID Cita", "Fecha", "Hora", "Médico", "Paciente"};
        tableModel = new DefaultTableModel(columnNames,0);
        tableCitas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCitas);
        add(scrollPane, BorderLayout.CENTER);
    }

        private void cargarDatosalSeleccionar() {// ES CAMBIA PARA INTENTAR QUE FUNCIONE ACTUALIZAR
    tableCitas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = tableCitas.getSelectedRow();
            if (selectedRow != -1) {
                String idCita = (String) tableModel.getValueAt(selectedRow, 0);
                Cita cita = citasBusiness.obtenerCitaPorId(idCita);
                if (cita != null) {
                    dateChooser.setDate(java.sql.Date.valueOf(cita.getFecha()));
                    timeSpinner.setValue(java.sql.Time.valueOf(cita.getHora()));
                    cboMedicos.setSelectedItem(cita.getMedico());
                }
            }
        }
    });
}

    private Medico[] getMedicos() {
        List<Medico> medicos = medicosBusiness.obtenerMedicos();
        return medicos.toArray(new Medico[0]);
    }

    private Medico getSelectedMedico() {
        return (Medico) cboMedicos.getSelectedItem();
    }

    private void registrarCita() {
       
       
        try {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha.");
                return;
            }
            LocalDate fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date spinnerDate = (Date) timeSpinner.getValue();
            LocalTime hora = spinnerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            Medico medico = getSelectedMedico();

            if (pacienteActual == null) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el paciente actual.");
                return;
            }

            if (fecha.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, "La fecha no puede ser en el pasado.");
                return;
            }

            if (hora.isBefore(LocalTime.of(8, 0)) || hora.isAfter(LocalTime.of(17, 0))) {
                JOptionPane.showMessageDialog(null, "La hora debe estar entre 8:00 AM y 5:00 PM.");
                return;
            }

            Cita nuevaCita = new Cita(UUID.randomUUID().toString(), fecha, hora, medico, pacienteActual);

            if (citasBusiness.registrarCita(nuevaCita)) {
                JOptionPane.showMessageDialog(null, "Cita programada con éxito.");
                
               // tableModel.addRow(new Object[]{nuevaCita.getCitaId(),nuevaCita.getFecha(),nuevaCita.getHora(),nuevaCita.getMedico(),nuevaCita.getPaciente()});
                actualizarTablaCitas();
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al programar la cita.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos ingresados: " + ex.getMessage());
        }
    }

    private void actualizarCita() {// SE MODIFICA PARA QUE FUNCIONE ACTUALIZAR
    int selectedRow = tableCitas.getSelectedRow();
    if (selectedRow != -1) {
        String idCita = (String) tableModel.getValueAt(selectedRow, 0); // ID de la cita
        Date selectedDate = dateChooser.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha.");
            return;
        }
        LocalDate fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date spinnerDate = (Date) timeSpinner.getValue();
        LocalTime hora = spinnerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        Medico medico = getSelectedMedico();

        if (pacienteActual == null) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el paciente actual.");
            return;
        }

        if (fecha.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "La fecha no puede ser en el pasado.");
            return;
        }

        if (hora.isBefore(LocalTime.of(8, 0)) || hora.isAfter(LocalTime.of(17, 0))) {
            JOptionPane.showMessageDialog(null, "La hora debe estar entre 8:00 AM y 5:00 PM.");
            return;
        }

        Cita cita = citasBusiness.obtenerCitaPorId(idCita);
        if (cita != null) {
            cita.setFecha(fecha);
            cita.setHora(hora);
            cita.setMedico(medico);

            if (citasBusiness.actualizarCita(cita)) {
                JOptionPane.showMessageDialog(null, "Cita actualizada exitosamente.");
                actualizarTablaCitas(); // Actualiza la tabla después de la modificación
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la cita.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cita no encontrada.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Selecciona una cita para actualizar.");
    }
}

    private void eliminarCita() throws IOException {
        int selectedRow = tableCitas.getSelectedRow();
        if (selectedRow != -1) {
            String idCita = (String) tableModel.getValueAt(selectedRow, 0); // ID de la cita
            Cita cita = citasBusiness.obtenerCitaPorId(idCita);
            if (cita != null) {
                if (citasBusiness.eliminarCita(idCita)) {
                    JOptionPane.showMessageDialog(null, "Cita cancelada exitosamente.");
                    actualizarTablaCitas();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cancelar la cita.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cita no encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una cita para cancelar.");
        }
    }

    private void actualizarTablaCitas() {
        tableModel.setRowCount(0);
        List<Cita> citas = citasBusiness.obtenerCitasPorPaciente(pacienteActual.getId());
        System.out.println("citas actualizar table"+citas);
        for (Cita cita : citas) {
            tableModel.addRow(new Object[]{
                cita.getCitaId(),
                cita.getFecha(),
                cita.getHora(),
                cita.getMedico().getNombreCompleto(),
                cita.getPaciente().getNombreCompleto()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProgramar) {
            registrarCita();
        } else if (e.getSource() == btnModificar) {
            actualizarCita();
        } else if (e.getSource() == btnCancelar) {
            try {
                eliminarCita();
            } catch (IOException ex) {
                Logger.getLogger(AgendaCitasForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

 

