package gui;

import controller.CategoriaController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistrarCategoria extends Ventana {
    public static void main(String[] args) {
        VentanaRegistrarCategoria ventana = new VentanaRegistrarCategoria();
    }

    // Componentes de la ventana: nombreCategoria, idCategoria
    private JLabel textoMenu, textoNombreCategoria, textoIDCategoria;
    private JTextField campoNombreCategoria, campoIDCategoria;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarCategoria() {
        super("Registrar Categoria", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoNombreCategoria();
        generarCampoIDCategoria();
        generarBotonRegistrar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Categoria";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 300, 50);
    }

    private void generarCampoNombreCategoria() {
        String textoNombreCategoria = "Nombre:";
        super.generarJLabel(this.textoNombreCategoria, textoNombreCategoria, 50, 150, 150, 30);
        campoNombreCategoria = super.generarJTextField(200, 150, 250, 30);
        this.add(campoNombreCategoria);
    }

    private void generarCampoIDCategoria() {
        String textoIDCategoria = "ID:";
        super.generarJLabel(this.textoIDCategoria, textoIDCategoria, 50, 200, 150, 30);
        campoIDCategoria = super.generarJTextField(200, 200, 250, 30);
        this.add(campoIDCategoria);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Categoria";
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

    // Método registrarCategoria validando su existencia
    private boolean registrarCategoria() throws ClassNotFoundException, IllegalArgumentException {
        String nombre = campoNombreCategoria.getText();
        if (nombre.isEmpty() || campoIDCategoria.getText().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar llenos.");
        }

        try {
            int id = Integer.parseInt(campoIDCategoria.getText());
            if (CategoriaController.registrarCategoria(nombre, id)) {
                JOptionPane.showMessageDialog(this, "Categoria registrada correctamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "La categoria ya existe o los datos son incorrectos");
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
                if (registrarCategoria()) {
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    limpiarCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Formato de número incorrecto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
            } catch (ClassNotFoundException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar la categoria: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        campoNombreCategoria.setText("");
        campoIDCategoria.setText("");
    }
}
