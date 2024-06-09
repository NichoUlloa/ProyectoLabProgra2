package gui;

import controller.MarcaController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistrarMarca extends Ventana {
    public static void main(String[] args) {
        VentanaRegistrarMarca ventana = new VentanaRegistrarMarca();
    }

    // Componentes de la ventana: nombreMarca, idMarca
    private JLabel textoMenu, textoNombreMarca, textoIDMarca;
    private JTextField campoNombreMarca, campoIDMarca;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarMarca() {
        super("Registrar Marca", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoNombreMarca();
        generarCampoIDMarca();
        generarBotonRegistrar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Marca";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 300, 50);
    }

    private void generarCampoNombreMarca() {
        String textoNombreMarca = "Nombre:";
        super.generarJLabel(this.textoNombreMarca, textoNombreMarca, 50, 150, 150, 30);
        campoNombreMarca = super.generarJTextField(200, 150, 250, 30);
        this.add(campoNombreMarca);
    }

    private void generarCampoIDMarca() {
        String textoIDMarca = "ID:";
        super.generarJLabel(this.textoIDMarca, textoIDMarca, 50, 200, 150, 30);
        campoIDMarca = super.generarJTextField(200, 200, 250, 30);
        this.add(campoIDMarca);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Marca";
        botonRegistrar = super.generarBoton(textoBoton, 75, 300, 150, 40);
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 300, 150, 40);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    // Método registrarMarca validando su existencia
    private boolean registrarMarca() throws ClassNotFoundException, IllegalArgumentException {
        String nombre = campoNombreMarca.getText();
        if (nombre.isEmpty() || campoIDMarca.getText().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar llenos.");
        }

        try {
            int id = Integer.parseInt(campoIDMarca.getText());
            if (MarcaController.registrarMarca(nombre, id)) {
                JOptionPane.showMessageDialog(this, "Marca registrada correctamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "La marca ya existe o los datos son incorrectos");
                return false;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID debe ser un número entero.");
        }
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            try {
                if (registrarMarca()) {
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    limpiarCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Formato de número incorrecto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
            } catch (ClassNotFoundException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar la marca: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                ex.printStackTrace();
            }
        }
        if (e.getSource() == botonCancelar) {
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }
    }

    private void limpiarCampos() {
        campoNombreMarca.setText("");
        campoIDMarca.setText("");
    }
}
