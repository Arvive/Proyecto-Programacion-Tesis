/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import business.MedicosBusiness;
import domain.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarMedicoForm extends JFrame {
    private JTextField txtNombreCompleto;
    private JTextField txtEspecialidad;
    private JTextField txtUbicacion;
    private JButton btnRegistrar;

    private MedicosBusiness medicosBusiness;

    public RegistrarMedicoForm() {
        medicosBusiness = new MedicosBusiness();

        setTitle("Registrar Médico");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
        lblNombreCompleto.setBounds(20, 20, 150, 25);
        add(lblNombreCompleto);

        txtNombreCompleto = new JTextField();
        txtNombreCompleto.setBounds(180, 20, 180, 25);
        add(txtNombreCompleto);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(20, 60, 150, 25);
        add(lblEspecialidad);

        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(180, 60, 180, 25);
        add(txtEspecialidad);

        JLabel lblUbicacion = new JLabel("Ubicación:");
        lblUbicacion.setBounds(20, 100, 150, 25);
        add(lblUbicacion);

        txtUbicacion = new JTextField();
        txtUbicacion.setBounds(180, 100, 180, 25);
        add(txtUbicacion);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(20, 140, 340, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCompleto = txtNombreCompleto.getText();
                String especialidad = txtEspecialidad.getText();
                String ubicacion = txtUbicacion.getText();
                
                String medicoId = String.valueOf(System.currentTimeMillis()); // Generar un ID único
                Medico nuevoMedico = new Medico(medicoId, nombreCompleto, especialidad, ubicacion);
                
                if (medicosBusiness.registrarMedico(nuevoMedico)) {
                    JOptionPane.showMessageDialog(null, "Médico registrado con éxito.");
                    txtNombreCompleto.setText("");
                    txtEspecialidad.setText("");
                    txtUbicacion.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el médico.");
                }
            }
        });
    }
}