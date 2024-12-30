/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Usuario
 */
public class SobreNosotrosForm extends JFrame {
    private JLabel logoImage;

    public SobreNosotrosForm() {
        setTitle("Sobre Nosotros");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout());

        // Panel superior con el título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("Sobre Nosotros");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel para la imagen del logo y el texto
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        
        // Imagen del logo
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Usuario\\OneDrive\\Documentos\\imageqa.png"); 
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(220, 60, Image.SCALE_SMOOTH); // Tamaño ajustado
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        logoImage = new JLabel(resizedIcon);
        logoImage.setHorizontalAlignment(JLabel.CENTER);
        panelCentral.add(logoImage, BorderLayout.NORTH);

        // Texto sobre la empresa
        JTextArea txtSobreNosotros = new JTextArea();
        txtSobreNosotros.setText(
                "Clínica Árbol de Seda\n\n"
                + "La Clínica Árbol de Seda es una institución médica comprometida con la excelencia en la atención al paciente. "
                + "Fundada con la visión de proporcionar un acceso más fácil y conveniente a servicios médicos de alta calidad, "
                + "nuestra clínica se ha convertido en un referente de salud en la comunidad.\n\n"
                + "Historia\n"
                + "La Clínica Árbol de Seda nació hace más de dos décadas con un objetivo claro: "
                + "revolucionar la manera en que las personas acceden a la atención médica. Desde nuestros humildes comienzos, "
                + "hemos crecido y evolucionado, integrando las últimas tecnologías en salud para ofrecer un servicio que se ajuste a las necesidades modernas de nuestros pacientes.\n\n"
                + "Misión\n"
                + "Nuestra misión es brindar atención médica de alta calidad a todos nuestros pacientes, garantizando un entorno seguro, cómodo y accesible para todos. "
                + "Nos esforzamos por ser un pilar de salud en la comunidad, atendiendo con respeto, ética y profesionalismo.\n\n"
                + "Visión\n"
                + "Ser líderes en innovación y calidad en la atención médica, ofreciendo soluciones integrales que mejoren la calidad de vida de nuestros pacientes. "
                + "Aspiramos a transformar el cuidado de la salud mediante el uso de tecnologías avanzadas, posicionándonos como la clínica preferida por la comunidad.\n\n"
                + "Valores\n"
                + "Compromiso: Estamos comprometidos con la salud y el bienestar de nuestros pacientes.\n"
                + "Innovación: Creemos en la mejora continua y en la incorporación de nuevas tecnologías para ofrecer el mejor servicio.\n"
                + "Empatía: Nos preocupamos profundamente por nuestros pacientes, brindando un cuidado personalizado.\n"
                + "Excelencia: Nos esforzamos por alcanzar la excelencia en todos los aspectos de nuestra atención médica.\n\n"
                + "Nuestro Equipo\n"
                + "Contamos con un equipo multidisciplinario que incluye médicos altamente calificados en diversas especialidades, personal administrativo dedicado y un equipo de desarrolladores comprometidos con la creación de este innovador sistema de citas médicas.\n\n"
                + "Médicos Especialistas: Nuestro equipo médico está compuesto por especialistas en diversas áreas como cardiología, pediatría, dermatología, entre otras. Todos nuestros médicos están comprometidos con brindar la mejor atención posible.\n\n"
                + "Desarrolladores de Tecnología: Detrás de este innovador sistema de citas médicas, se encuentra un equipo de desarrolladores apasionados por la tecnología y la salud. "
                + "Nos hemos enfocado en crear una plataforma que no solo sea funcional, sino también fácil de usar y accesible para todos nuestros usuarios.");
        txtSobreNosotros.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSobreNosotros.setWrapStyleWord(true);
        txtSobreNosotros.setLineWrap(true);
        txtSobreNosotros.setEditable(false);

        // ScrollPane para el JTextArea
        JScrollPane scrollPane = new JScrollPane(txtSobreNosotros);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Agregar texto al panel central
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        // Agregar el panel central al JFrame
        add(panelCentral, BorderLayout.CENTER);
    }
}