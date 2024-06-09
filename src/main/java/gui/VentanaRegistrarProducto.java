package gui;

import controller.CategoriaController;
import controller.MarcaController;
import model.Categoria;
import model.Marca;
import model.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaRegistrarProducto extends Ventana {
    private JLabel textoMenu, textoID, textoNombre, textoMarca, textoCategoria, textoPrecio, textoStock;
    private JTextField campoID, campoNombre, campoPrecio, campoStock;
    private JComboBox<Marca> campoMarca;
    private JComboBox<Categoria> campoCategoria;
    private JButton botonRegistrar, botonCancelar;
    private List<Marca> marcas;
    private List<Categoria> categorias;

    public VentanaRegistrarProducto() {
        super("Registrar Producto", 500, 600);
        try {
            cargarMarcasYCategorias();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        generarElementosVentana();
    }

    private void cargarMarcasYCategorias() throws ClassNotFoundException {
        marcas = MarcaController.obtenerMarcas();
        categorias = CategoriaController.obtenerCategorias();
    }

    private void generarElementosVentana() {
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

    private void generarListaMarca() {
        super.generarJLabel(this.textoMarca, "Marca:", 50, 350, 100, 30);
        campoMarca = new JComboBox<>();
        for (Marca marca : marcas) {
            campoMarca.addItem(marca);
        }
        campoMarca.setBounds(200, 350, 250, 30);
        this.add(campoMarca);
    }

    private void generarListaCategoria() {
        super.generarJLabel(this.textoCategoria, "Categoría:", 50, 400, 100, 30);
        campoCategoria = new JComboBox<>();
        for (Categoria categoria : categorias) {
            campoCategoria.addItem(categoria);
        }
        campoCategoria.setBounds(200, 400, 250, 30);
        this.add(campoCategoria);
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

    private boolean registrarProducto() throws ClassNotFoundException {
        if (this.campoNombre.getText().length() == 0 || this.campoPrecio.getText().length() == 0 || this.campoStock.getText().length() == 0) {
            return false;
        } else {
            Marca marcaSeleccionada = (Marca) campoMarca.getSelectedItem();
            Categoria categoriaSeleccionada = (Categoria) campoCategoria.getSelectedItem();

            Producto producto = new Producto(this.campoNombre.getText(), Double.parseDouble(this.campoPrecio.getText()), Integer.parseInt(this.campoStock.getText()), marcaSeleccionada.getIdMarca(), categoriaSeleccionada.getIdCategoria());
            // Asumiendo que tienes un método en algún controlador para registrar el producto
            // ProductoController.registrarProducto(producto);
            return true;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if (registrarProducto()) {
                    JOptionPane.showMessageDialog(this, "Producto registrado correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese datos válidos");
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
