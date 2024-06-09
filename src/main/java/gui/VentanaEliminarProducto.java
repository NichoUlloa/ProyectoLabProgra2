package gui;

import controller.ProductoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaEliminarProducto extends Ventana {
    public static void main(String[] args) {
        VentanaEliminarProducto ventana = new VentanaEliminarProducto();
    }

    // Componentes de la ventana
    private JLabel textoMenu, textoID;
    private JTextField campoID;
    private JButton botonEliminar, botonCancelar;

    public VentanaEliminarProducto() {
        super("Eliminar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoID();
        generarBotonEliminar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Eliminar Producto";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 300, 50);
    }

    private void generarCampoID() {
        String textoID = "ID:";
        super.generarJLabel(this.textoID, textoID, 50, 150, 150, 30);
        campoID = super.generarJTextField(200, 150, 250, 30);
        this.add(campoID);
    }

    private void generarBotonEliminar() {
        String textoBoton = "Eliminar Producto";
        botonEliminar = super.generarBoton(textoBoton, 75, 300, 150, 40);
        botonEliminar.addActionListener(this);
        this.add(botonEliminar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 300, 150, 40);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    // Método eliminarProducto validando su existencia
    private void eliminarProducto() throws ClassNotFoundException {
        ProductoController productoController = new ProductoController();
        int id = Integer.parseInt(campoID.getText());
        if (productoController.existeProducto(id)) {
            productoController.eliminarProducto(id);
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El producto con ID " + id + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEliminar) {
            try {
                eliminarProducto();
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == botonCancelar) {
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }
    }
}
