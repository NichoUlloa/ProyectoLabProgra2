package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.InternationalFormatter;

public class Ventana extends JFrame implements ActionListener {
    private final Font fuenteTitulo;
    private final Font fuenteTexto;

    protected Ventana(String nombre, int largoX, int largoY) {
        super(nombre);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(largoX, largoY);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(173, 216, 230)); // Fondo azul claro
        this.setLayout(null);
        this.fuenteTitulo = new Font("Times New Roman", Font.BOLD, 20);
        this.fuenteTexto = new Font("Times New Roman", Font.PLAIN, 14);
    }

    protected void generarJLabelEncabezado(JLabel label, String texto, int largoX, int largoY) {
        label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(this.fuenteTitulo);
        label.setBounds((this.getWidth() - largoX) / 2, 20, largoX, largoY); // Centrado horizontalmente
        this.add(label);
    }

    protected JButton generarBoton(String texto, int posicionX, int posicionY, int largoX, int largoY) {
        JButton boton = new RoundedButton(texto); // Usa RoundedButton en lugar de JButton
        boton.setBounds(posicionX, posicionY, largoX, largoY);
        boton.setFont(this.fuenteTexto);
        boton.setBackground(new Color(0, 120, 215)); // Color azul para el fondo del botón
        boton.setForeground(Color.WHITE); // Color blanco para el texto del botón
        return boton;
    }

    protected void generarJLabel(JLabel label, String texto, int posicionX, int posicionY, int largoX, int largoY) {
        label = new JLabel(texto);
        label.setBounds(posicionX, posicionY, largoX, largoY);
        label.setFont(this.fuenteTexto);
        this.add(label);
    }

    protected JFormattedTextField generarJFormattedTextField(InternationalFormatter formato, int posicionX, int posicionY, int largoX, int largoY) {
        JFormattedTextField textField = new JFormattedTextField(formato);
        textField.setBounds(posicionX, posicionY, largoX, largoY);
        return textField;
    }

    protected InternationalFormatter generarFormato(int minimo) {
        InternationalFormatter formato = new InternationalFormatter();
        formato.setMinimum(minimo);
        return formato;
    }

    protected InternationalFormatter generarFormato(int minimo, int maximo) {
        InternationalFormatter formato = new InternationalFormatter();
        formato.setMinimum(minimo);
        formato.setMaximum(maximo);
        return formato;
    }

    protected JTextField generarJTextField(int posicionX, int posicionY, int largoX, int largoY) {
        JTextField textField = new JTextField();
        textField.setBounds(posicionX, posicionY, largoX, largoY);
        return textField;
    }

    protected JRadioButton generarJRadioButton(String texto, int posicionX, int posicionY, int largoX, int largoY) {
        JRadioButton boton = new JRadioButton(texto);
        boton.setBounds(posicionX, posicionY, largoX, largoY);
        boton.setFont(this.fuenteTexto);
        return boton;
    }

    protected JComboBox<Object> generarListaDesplegable(Object[] datosLista, int posicionX, int posicionY, int largoX, int largoY) {
        JComboBox<Object> lista = new JComboBox<>(datosLista);
        lista.setBounds(posicionX, posicionY, largoX, largoY);
        lista.setFont(this.fuenteTexto);
        return lista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
