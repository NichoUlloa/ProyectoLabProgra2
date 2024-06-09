package gui;

import controller.CategoriaController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistrarCategoria extends Ventana {
    private JLabel textoMenu, textoNombreCategoria;
    private JTextField campoNombreCategoria;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarCategoria() {
        super("Registrar Categoria", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoNombreCategoria();
        generarBotonRegistrar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Categoria";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 200, 50);
    }

    private void generarCampoNombreCategoria() {
        String textoNombreCategoria = "Nombre:";
        super.generarJLabel(this.textoNombreCategoria, textoNombreCategoria, 20, 50, 150, 20);
        campoNombreCategoria = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombreCategoria);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Categoria";
        botonRegistrar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 170, 20);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    private boolean registrarCategoria() throws ClassNotFoundException {
        if (this.campoNombreCategoria.getText().length() == 0) {
            return false;
        } else {
            return CategoriaController.registrarCategoria(this.campoNombreCategoria.getText());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if (registrarCategoria()) {
                    JOptionPane.showMessageDialog(this, "Categoria registrada correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Categoria ya ingresada o datos incorrectos");
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == this.botonCancelar) {
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }
    }
}
