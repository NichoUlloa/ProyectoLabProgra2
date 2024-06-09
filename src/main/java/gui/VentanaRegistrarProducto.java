package gui;

import controller.ProductoController;
import controller.MarcaController;
import controller.CategoriaController;
import model.Categoria;
import model.Marca;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistrarProducto extends Ventana {
    public static void main(String[] args) throws ClassNotFoundException {
        VentanaRegistrarProducto ventana = new VentanaRegistrarProducto();
    }

    // Componentes de la ventana
    private JLabel textoMenu, textoID, textoNombre, textoMarca, textoCategoria, textoPrecio, textoStock;
    private JTextField campoID, campoNombre, campoPrecio, campoStock;
    private JComboBox campoMarca;
    private JComboBox campoCategoria;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarProducto() throws ClassNotFoundException{
        super("Registrar Producto", 500, 600); // Ajuste de tamaño para acomodar el nuevo campo
        generarElementosVentana();
    }

    private void generarElementosVentana() throws ClassNotFoundException{
        generarMensajeMenu();
        generarCampoID();
        generarCampoNombre();
        generarCampoPrecio();
        generarCampoStock();
        generarListaMarca();
        generarListaCategoria();
        generarBotonRegistrar();
        generarBotonCancelar();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Producto";
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

    private void generarCampoStock() {
        String textoStock = "Stock:";
        super.generarJLabel(this.textoStock, textoStock, 50, 300, 150, 30);
        campoStock = super.generarJTextField(200, 300, 250, 30);
        this.add(campoStock);
    }

    private void generarListaMarca()  throws ClassNotFoundException{
        super.generarJLabel(this.textoMarca, "Marca:", 50, 350, 150, 30);
        this.campoMarca = super.generarListaDesplegable(MarcaController.getIDMarcas(), 200, 350, 250, 30);
        this.add(this.campoMarca);

    }

    private void generarListaCategoria() throws ClassNotFoundException {
        super.generarJLabel(this.textoCategoria, "Categoria:", 50, 400, 150, 30);
        this.campoCategoria = super.generarListaDesplegable(CategoriaController.getIDCategorias(), 200, 400, 250, 30);
        this.add(this.campoCategoria);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Producto";
        botonRegistrar = super.generarBoton(textoBoton, 75, 500, 150, 40);
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 500, 150, 40);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    private boolean registrarProducto() throws ClassNotFoundException, IllegalArgumentException {
        if (campoID.getText().isEmpty() || campoNombre.getText().isEmpty() || campoPrecio.getText().isEmpty() || campoStock.getText().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar llenos.");
        }
        int id = Integer.parseInt(campoID.getText());
        String nombre = campoNombre.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        int stock = Integer.parseInt(campoStock.getText());
        Marca marca = (Marca) campoMarca.getSelectedItem();
        Categoria categoria = (Categoria) campoCategoria.getSelectedItem();

        // int id, String nombre, Marca marca, Categoria categoria, double precio, int stock
        return ProductoController.registrarProducto(id, nombre, marca, categoria, precio, stock);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            try {
                if (registrarProducto()) {
                    JOptionPane.showMessageDialog(this, "Producto registrado correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Producto ya ingresado o datos incorrectos");
                    limpiarCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Formato de número incorrecto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
            } catch (ClassNotFoundException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        campoPrecio.setText("");
        campoStock.setText("");
        campoMarca.setSelectedIndex(0);
        campoCategoria.setSelectedIndex(0);
    }
}
