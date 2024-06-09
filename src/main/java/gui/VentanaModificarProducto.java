package gui;

import controller.ProductoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaModificarProducto extends Ventana {
    public static void main(String[] args) {
        VentanaModificarProducto ventana = new VentanaModificarProducto();
    }

    // Componentes de la ventana
    private JLabel textoMenu, textoID, textoNombre, textoPrecio;
    private JTextField campoID, campoNombre, campoPrecio;
    private JButton botonModificar, botonCancelar;

    public VentanaModificarProducto() {
        super("Modificar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoID();
        generarCampoNombre();
        generarCampoPrecio();
        generarBotonModificar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Modificar Producto";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 300, 50);
    }

    private void generarCampoID() {
        String textoID = "ID:";
        super.generarJLabel(this.textoID, textoID, 50, 150, 150, 30);
        campoID = super.generarJTextField(200, 150, 250, 30);
        this.add(campoID);
    }

    private void generarCampoNombre() {
        String textoNombre = "Nombre:";
        super.generarJLabel(this.textoNombre, textoNombre, 50, 200, 150, 30);
        campoNombre = super.generarJTextField(200, 200, 250, 30);
        this.add(campoNombre);
    }

    private void generarCampoPrecio() {
        String textoPrecio = "Precio:";
        super.generarJLabel(this.textoPrecio, textoPrecio, 50, 250, 150, 30);
        campoPrecio = super.generarJTextField(200, 250, 250, 30);
        this.add(campoPrecio);
    }

    private void generarBotonModificar() {
        String textoBoton = "Modificar Producto";
        botonModificar = super.generarBoton(textoBoton, 75, 350, 150, 40);
        botonModificar.addActionListener(this);
        this.add(botonModificar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 350, 150, 40);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonModificar) {
            try {
                int id = Integer.parseInt(campoID.getText());
                String nombre = campoNombre.getText();
                double precio = Double.parseDouble(campoPrecio.getText());

                ProductoController productoController = new ProductoController();

                if (productoController.existeProducto(id)) {
                    productoController.modificarProducto(id, nombre, precio);
                    JOptionPane.showMessageDialog(this, "Producto modificado correctamente");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "El producto con ID " + id + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    limpiarCampos();
                }

            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(this, "Error al modificar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                classNotFoundException.printStackTrace();
                limpiarCampos();
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese datos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
            }
        } else if (e.getSource() == botonCancelar) {
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }
    }

    private void limpiarCampos() {
        campoID.setText("");
        campoNombre.setText("");
        campoPrecio.setText("");
    }
}
