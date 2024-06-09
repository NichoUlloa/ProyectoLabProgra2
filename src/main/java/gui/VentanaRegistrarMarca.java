package gui;

import controller.MarcaController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistrarMarca extends Ventana {
    private JLabel textoMenu, textoNombreMarca;
    private JTextField campoNombreMarca;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarMarca() {
        super("Registrar Marca", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoNombreMarca();
        generarBotonRegistrar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Marca";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 200, 50);
    }

    private void generarCampoNombreMarca() {
        String textoNombreMarca = "Nombre:";
        super.generarJLabel(this.textoNombreMarca, textoNombreMarca, 20, 50, 150, 20);
        campoNombreMarca = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombreMarca);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Marca";
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

    private boolean registrarMarca() throws ClassNotFoundException {
        if (this.campoNombreMarca.getText().length() == 0) {
            return false;
        } else {
            return MarcaController.registrarMarca(this.campoNombreMarca.getText());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if (registrarMarca()) {
                    JOptionPane.showMessageDialog(this, "Marca registrada correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Marca ya ingresada o datos incorrectos");
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
