package gui;

import controller.ProductoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaBienvenida extends Ventana {
    public static void main(String[] args) {
        VentanaBienvenida ventana = new VentanaBienvenida();
    }

    // Componentes de la ventana
    private JLabel textoMenu;
    private JButton mostrarProductos;
    private JButton registrarProducto;
    private JButton eliminarProducto;
    private JButton modificarProducto;
    private JButton buscarProducto;
    private JButton registrarMarca;
    private JButton registrarCategoria;
    private JButton salir;

    public VentanaBienvenida() {
        super("Inventory Plus Manager", 500, 620); // Ajuste de tamaño para acomodar los nuevos botones
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonMostrarProductos();
        generarBotonRegistrarProducto();
        generarBotonEliminarProducto();
        generarBotonModificarProducto();
        generarBotonBuscarProducto();
        generarBotonRegistrarMarca();
        generarBotonRegistrarCategoria();
        generarBotonSalida();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "INVENTORY PLUS MANAGER";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 300, 50);
    }

    private void generarBotonMostrarProductos() {
        mostrarProductos = super.generarBoton("Mostrar Productos", 150, 70, 200, 40);
        mostrarProductos.addActionListener(this);
        this.add(mostrarProductos);
    }

    private void generarBotonRegistrarProducto() {
        registrarProducto = super.generarBoton("Registrar Producto", 150, 120, 200, 40);
        registrarProducto.addActionListener(this);
        this.add(registrarProducto);
    }

    private void generarBotonEliminarProducto() {
        eliminarProducto = super.generarBoton("Eliminar Producto", 150, 170, 200, 40);
        eliminarProducto.addActionListener(this);
        this.add(eliminarProducto);
    }

    private void generarBotonModificarProducto() {
        modificarProducto = super.generarBoton("Modificar Producto", 150, 220, 200, 40);
        modificarProducto.addActionListener(this);
        this.add(modificarProducto);
    }

    private void generarBotonBuscarProducto() {
        buscarProducto = super.generarBoton("Buscar Producto", 150, 270, 200, 40);
        buscarProducto.addActionListener(this);
        this.add(buscarProducto);
    }

    private void generarBotonRegistrarMarca() {
        registrarMarca = super.generarBoton("Registrar Marca", 150, 320, 200, 40);
        registrarMarca.addActionListener(this);
        this.add(registrarMarca);
    }

    private void generarBotonRegistrarCategoria() {
        registrarCategoria = super.generarBoton("Registrar Categoría", 150, 370, 200, 40);
        registrarCategoria.addActionListener(this);
        this.add(registrarCategoria);
    }

    private void generarBotonSalida() {
        salir = super.generarBoton("Salir", 150, 470, 200, 40);
        salir.addActionListener(this);
        this.add(salir);
    }

    // Override del método actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mostrarProductos) {
            try {
                String[][] datosProductos = ProductoController.mostrarTodosLosProductos();
                VentanaTabla ventanaTabla = new VentanaTabla(datosProductos, new String[]{"ID", "Nombre", "Marca", "Categoría", "Precio"});
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == registrarProducto) {
            try {
                VentanaRegistrarProducto ventanaRegistrarProducto = new VentanaRegistrarProducto();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        } else if (e.getSource() == eliminarProducto) {
            VentanaEliminarProducto ventanaEliminarProducto = new VentanaEliminarProducto();
            this.dispose();
        } else if (e.getSource() == modificarProducto) {
            VentanaModificarProducto ventanaModificarProducto = new VentanaModificarProducto();
            this.dispose();
        } else if (e.getSource() == buscarProducto) {
            VentanaBuscarProducto ventanaBuscarProducto = new VentanaBuscarProducto();
            this.dispose();
        } else if (e.getSource() == registrarMarca) {
            VentanaRegistrarMarca ventanaRegistrarMarca = new VentanaRegistrarMarca();
            this.dispose();
        } else if (e.getSource() == registrarCategoria) {
            VentanaRegistrarCategoria ventanaRegistrarCategoria = new VentanaRegistrarCategoria();
            this.dispose();
        } else if (e.getSource() == salir) {
            System.exit(0);
        }
    }
}
