package view;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import static javax.swing.SwingConstants.CENTER;

public abstract class GUIFactory extends JFrame {

    protected GUIFactory(int ancho, int largo) {
        dimensionar(ancho, largo);
        decorar();
        inicializar();
    }

    protected void cargarElementos() {
        cargarPaneles();
        cargarBotones();
        cargarTextos();
    }

    protected abstract void cargarBotones();

    protected abstract void cargarTextos();

    protected abstract void cargarPaneles();

    protected void asignarElementos() {
        asignarAFrame();
        asignarAPaneles();
    }

    protected abstract void asignarAFrame();

    protected abstract void asignarAPaneles();

    protected abstract void visibilizarPaneles();

    protected abstract void setActionListener(ActionListener listener);

    private void inicializar() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void dimensionar(int ancho, int largo) {
        this.setResizable(false);
        this.setSize(ancho, largo);
    }

    private void decorar() {
        this.setLayout(null);
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(0, 0,
                this.getWidth(), this.getHeight(),
                40, 40));
        this.getContentPane().setBackground(new Color(30, 50, 70));
    }

    protected JTextField crearJTextField(int x, int y,
                                         int ancho, int largo) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, ancho, largo);
        return textField;
    }

    protected JButton crearJButton(int x, int y,
                                   int ancho, int largo,
                                   String texto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, largo);

        boton.setBackground(new Color(150, 180, 210));
        boton.setForeground(Color.WHITE);

        return boton;
    }

    protected JButton crearJButton(String texto) {
        JButton boton = new JButton(texto);

        boton.setBackground(new Color(150, 180, 210));
        boton.setForeground(Color.WHITE);

        return boton;
    }

    protected JButton crearJButton(int ancho, int largo,
                                   String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(ancho, largo));

        boton.setBackground(new Color(150, 180, 210));
        boton.setForeground(Color.WHITE);

        return boton;
    }

    protected JLabel crearTitulo(String texto,
                                 int x, int y,
                                 int ancho, int largo) {
        JLabel titulo = new JLabel(texto);
        titulo.setBounds(x, y, ancho, largo);
        titulo.setHorizontalAlignment(CENTER);
        titulo.setForeground(Color.WHITE);
        Font fuenteUsada = new Font("Arial", 1, 35);
        titulo.setFont(fuenteUsada);
        return titulo;
    }

    protected JLabel crearSubtitulo(String texto,
                                    int x, int y,
                                    int ancho, int largo) {
        JLabel titulo = new JLabel(texto);
        titulo.setBounds(x, y, ancho, largo);
        titulo.setHorizontalAlignment(CENTER);
        titulo.setForeground(Color.WHITE);
        Font fuenteUsada = new Font("Arial", 1, 20);
        titulo.setFont(fuenteUsada);
        return titulo;
    }

    protected JPanel crearPanel(int x, int y,
                                int ancho, int largo) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(60, 80, 100));
        panel.setBounds(x, y, ancho, largo);
        panel.setVisible(false);
        return panel;
    }

    protected JTextPane crearTextPane(String texto,
                                      int x, int y,
                                      int ancho, int largo) {
        JTextPane textPane = new JTextPane();
        textPane.setText(texto);
        textPane.setEditable(false);
        textPane.setBounds(x, y,
                ancho, largo);
        textPane.setOpaque(false);

        Font fuenteUsada = new Font("Arial", 1, 20);
        textPane.setFont(fuenteUsada);
        textPane.setForeground(Color.WHITE);

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet centrar = new SimpleAttributeSet();
        StyleConstants.setAlignment(centrar, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), centrar, false);

        return textPane;
    }

}


