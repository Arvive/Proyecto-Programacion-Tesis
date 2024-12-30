/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipalForm extends JFrame {
    private JButton btnAgendaCitas;
    private JButton btnBusquedaMedicos;
    private JButton btnRegistroUsuarios;
    private JButton btnInicioSesion;
    private JButton btnSobreNosotros;
    private JLabel logoImage;
    private JLabel especialidadesImage;
    private JLabel especialidadesText;
    private JButton btnRegistrarMedico;
    private JLabel descripcionLabel;

    public PantallaPrincipalForm() {
        setTitle("Clínica Árbol de Seda");
        setSize(650, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        btnAgendaCitas = new JButton("Agenda de Citas");
        btnAgendaCitas.setBounds(18, 160, 200, 30);
        add(btnAgendaCitas);

        btnBusquedaMedicos = new JButton("Búsqueda de Médicos");
        btnBusquedaMedicos.setBounds(18, 200, 200, 30);
        add(btnBusquedaMedicos);

        btnRegistroUsuarios = new JButton("Registro de Usuarios");
        btnRegistroUsuarios.setBounds(18, 240, 200, 30);
        add(btnRegistroUsuarios);

        btnInicioSesion = new JButton("Inicio de Sesión");
        btnInicioSesion.setBounds(18, 280, 200, 30);
        add(btnInicioSesion);

        btnSobreNosotros = new JButton("Sobre Nosotros");
        btnSobreNosotros.setBounds(18, 320, 200, 30);
        add(btnSobreNosotros);
        
        btnRegistrarMedico = new JButton("Registrar Médico"); 
        btnRegistrarMedico.setBounds(18, 360, 200, 30);
        add(btnRegistrarMedico);
        
        //LOGO
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Usuario\\OneDrive\\Documentos\\imageqa.png"); 
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(220, 60, Image.SCALE_SMOOTH); // Tamaño ajustado (300x150)
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        logoImage = new JLabel(resizedIcon);
        logoImage.setBounds(6, 50, resizedIcon.getIconWidth(), resizedIcon.getIconHeight());
        add(logoImage);
        
        //texto arriba de la segunda imagen
        especialidadesText = new JLabel("Especialidades");
        especialidadesText.setFont(new Font("Times New Roman", Font.BOLD, 17));
        especialidadesText.setBounds(380, 40, 150, 30); // Ajustar posición y tamaño
        panel.add(especialidadesText);
        
        //ESPECIALIDADES
        ImageIcon secondImageIcon = new ImageIcon("C:\\Users\\Usuario\\OneDrive\\Documentos\\Diseño sin título.png");
        Image secondOriginalImage = secondImageIcon.getImage();
        Image secondResizedImage = secondOriginalImage.getScaledInstance(280, 320, Image.SCALE_SMOOTH); // Tamaño ajustado
        ImageIcon secondResizedIcon = new ImageIcon(secondResizedImage);

        especialidadesImage = new JLabel(secondResizedIcon);
        especialidadesImage.setBounds(300, 73, secondResizedIcon.getIconWidth(), secondResizedIcon.getIconHeight()); // Ajustar posición
        panel.add(especialidadesImage);
        
        add(panel,BorderLayout.CENTER);
        
        //descripcion del sistema
        JPanel descripcionPanel = new JPanel();
        descripcionPanel.setBackground(Color.white);
        descripcionPanel.setLayout(new BorderLayout());

        descripcionLabel = new JLabel("<html><center>Bienvenido a la Clínica Árbol de Seda.<br>" +
                                     "Ofrecemos una amplia gama de servicios médicos especializados para su bienestar.<br>" +
                                     "Para más información, consulte nuestras secciones en el menú.</center></html>");
        descripcionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descripcionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descripcionPanel.add(descripcionLabel, BorderLayout.CENTER);

        add(descripcionPanel, BorderLayout.SOUTH);
        
        

        btnAgendaCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgendaCitasForm().setVisible(true);
            }
        });

        btnBusquedaMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BusquedaMedicosForm().setVisible(true);
            }
        });

        btnRegistroUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistroUsuariosForm().setVisible(true);
            }
        });

        btnInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InicioSesionForm().setVisible(true);
            }
        });

        btnSobreNosotros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SobreNosotrosForm().setVisible(true);
            }
        });
        
         btnRegistrarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarMedicoForm().setVisible(true);
            }
        });
    }
}