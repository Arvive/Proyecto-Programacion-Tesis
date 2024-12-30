/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;


import business.PacienteBusiness;
import business.SesionBusiness;
import business.UsuarioBusiness;
import domain.Paciente;
import domain.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesionForm extends JFrame {
    private JTextField txtCedula;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    private UsuarioBusiness usuarioBusiness;

    public InicioSesionForm() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        usuarioBusiness = new UsuarioBusiness();

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(20, 20, 100, 25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(120, 20, 150, 25);
        add(txtCedula);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(20, 60, 100, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(120, 60, 150, 25);
        add(txtContrasena);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(120, 100, 150, 30);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
    }

    private void autenticarUsuario() {
    String cedula = txtCedula.getText();
    String contrasena = new String(txtContrasena.getPassword());

    Usuario usuario = usuarioBusiness.autenticarUsuario(cedula, contrasena);

    if (usuario != null) {
        SesionBusiness sesionBusiness = SesionBusiness.getInstancia();
        sesionBusiness.setUsuarioAutenticado(usuario);

        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. Bienvenido " + usuario.getNombreCompleto());
        dispose(); // Cierra la ventana de inicio de sesión
        new AgendaCitasForm().setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Cédula o contraseña incorrectos.");
 
}
}
}