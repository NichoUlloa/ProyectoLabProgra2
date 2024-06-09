package gui;

import controller.ProductoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaBuscarProducto extends Ventana {
    public static void main(String[] args) {
        VentanaBuscarProducto ventana = new VentanaBuscarProducto();
    }

    // componentes de la ventana
    private JLabel textoMenu, textoID, textoNombre;
    private JTextField campoID, campoNombre;
    private JButton botonBuscar, botonCancelar;

    public VentanaBuscarProducto() {
        super("Buscar Producto", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarCampoID();
        generarCampoNombre();
        generarBotonBuscar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Buscar Producto";
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

    private void generarBotonBuscar() {
        String textoBoton = "Buscar Producto";
        botonBuscar = super.generarBoton(textoBoton, 75, 300, 150, 40);
        botonBuscar.addActionListener(this);
        this.add(botonBuscar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 300, 150, 40);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    // Metodo exportarDatosProducto
    private String[][] exportarDatosProducto() throws ClassNotFoundException, IllegalArgumentException {
        if (campoID.getText().length() == 0 && campoNombre.getText().length() == 0) {
            throw new IllegalArgumentException("Ingrese datos válidos");
        } else if (campoID.getText().length() == 0) {
            return ProductoController.mostrarProductosNombre(campoNombre.getText());
        } else {
            return ProductoController.mostrarProductosID(Integer.parseInt(campoID.getText()));
        }
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonBuscar) {
            String[] nombreColumnas = {"ID", "Nombre", "Marca", "Categoria", "Precio"};
            try {
                String[][] datosProducto = exportarDatosProducto();
                if (datosProducto.length == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron productos con los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                    limpiarCampos();
                } else {
                    new VentanaTabla(datosProducto, nombreColumnas);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Formato de número incorrecto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
            } catch (ClassNotFoundException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                ex.printStackTrace();
            }
        } else if (e.getSource() == botonCancelar) {
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }
    }

    private void limpiarCampos() {
        campoID.setText("");
        campoNombre.setText("");
    }
}
