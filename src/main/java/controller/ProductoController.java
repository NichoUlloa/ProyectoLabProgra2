package controller;

import model.Categoria;
import model.Marca;
import model.Producto;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.ProductoDAO;
import org.jooq.DSLContext;

public class ProductoController {
    private static final String nombreDataBase = "GestionInventarioDB";

    // Los atributos de nuestra clase Producto son id, nombre, precio, stock, marca, categoria
    // Método registrarProducto validando su existencia
    public static boolean registrarProducto(int id, String nombre, Marca marca, Categoria categoria, double precio, int stock) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (!ProductoDAO.validarExistenciaProducto(query, "id", id)) {
            Producto producto = new Producto(id, nombre, precio, stock, marca, categoria);
            ProductoDAO.registrarProducto(query, producto);
            DBConnector.closeConnection();
            return true;
        } else {
            DBConnector.closeConnection();
            return false;
        }
    }

    public static String[][] mostrarTodosLosProductos() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductos(query);
        DBConnector.closeConnection();
        return datosProductos;
    }

    public static String[][] mostrarProductosID(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductoPorId(query, id);
        DBConnector.closeConnection();
        return datosProductos;
    }

    public static String[][] mostrarProductosNombre(String nombre) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductoPorNombre(query, nombre);
        DBConnector.closeConnection();
        return datosProductos;
    }

    // Mostrar productos por marca
    public static String[][] mostrarProductosMarca(Marca marca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductoPorMarca(query, marca);
        DBConnector.closeConnection();
        return datosProductos;
    }

    // Mostrar productos por categoría
    public static String[][] mostrarProductosCategoria(Categoria categoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosProductos = ProductoDAO.obtenerProductoPorCategoria(query, categoria);
        DBConnector.closeConnection();
        return datosProductos;
    }

    public static void modificarProducto(int id, String nombre, double precio) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        ProductoDAO.modificarProducto(query, id, nombre, precio);
        DBConnector.closeConnection();
    }

    // Método eliminarProducto validando su existencia
    public static void eliminarProducto(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (ProductoDAO.validarExistenciaProducto(query, "id", id)) {
            ProductoDAO.eliminarProducto(query, id);
            DBConnector.closeConnection();
        } else {
            DBConnector.closeConnection();
            throw new IllegalArgumentException("El producto no existe");
        }
    }

    // Nuevo método para verificar existencia del producto
    public static boolean existeProducto(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        boolean existe = ProductoDAO.validarExistenciaProducto(query, "id", id);
        DBConnector.closeConnection();
        return existe;
    }
}
