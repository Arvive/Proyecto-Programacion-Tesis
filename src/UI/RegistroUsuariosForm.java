/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import business.UsuarioBusiness;
import domain.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroUsuariosForm extends JFrame {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCelular;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar;
    
    private UsuarioBusiness usuarioBusiness;

    public RegistroUsuariosForm() {
        setTitle("Registro de Usuarios");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        usuarioBusiness = new UsuarioBusiness();

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(20, 20, 100, 25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(120, 20, 150, 25);
        add(txtCedula);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 150, 25);
        add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 100, 100, 25);
        add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(120, 100, 150, 25);
        add(txtApellidos);

        JLabel lblCelular = new JLabel("Celular:");
        lblCelular.setBounds(20, 140, 100, 25);
        add(lblCelular);

        txtCelular = new JTextField();
        txtCelular.setBounds(120, 140, 150, 25);
        add(txtCelular);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 180, 100, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 180, 150, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(20, 220, 100, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(120, 220, 150, 25);
        add(txtContrasena);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(120, 260, 150, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String id = txtCedula.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String celular = txtCelular.getText();
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());

        Paciente nuevoPaciente = new Paciente(id, nombre, apellidos, celular, correo, contrasena);

        boolean registrado = usuarioBusiness.registrarUsuario(nuevoPaciente);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario.");
        }
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
    }
}