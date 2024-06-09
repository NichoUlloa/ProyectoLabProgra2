package model.data.dao;

import model.Categoria;
import model.Marca;
import model.Producto;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class ProductoDAO {
    // Método para agregar un producto a la base de datos
    public static void registrarProducto(DSLContext query, Producto producto) {
        Table tablaProducto = table(name("Producto"));
        Field[] columnas = tablaProducto.fields("id", "nombre", "precio", "stock", "marca", "categoria");
        query.insertInto(tablaProducto, columnas[0], columnas[1], columnas[2], columnas[3], columnas[4], columnas[5])
                .values(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock(), producto.getMarca().getNombreMarca(), producto.getCategoria().getNombreCategoria())
                .execute();
    }

    // Método para validar si un producto ya existe en la base de datos
    public static boolean validarExistenciaProducto(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(DSL.table("Producto")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return resultados.size() >= 1;
    }

    // Método para modificar un producto en la base de datos
    public static void modificarProducto(DSLContext query, int id, String nombre, double precio) {
        query.update(DSL.table("Producto"))
                .set(DSL.field("nombre"), nombre)
                .set(DSL.field("precio"), precio)
                .where(DSL.field("id").eq(id)).execute();
    }

    // Método para eliminar un producto en la base de datos
    public static void eliminarProducto(DSLContext query, int id) {
        Table tablaProducto = table(name("Producto"));
        query.delete(DSL.table("Producto")).where(DSL.field("id").eq(id)).execute();
    }

    // Método para obtener un producto en la base de datos por ID
    public static String[][] obtenerProductoPorId(DSLContext query, int id) {
        Result resultados = query.select().from(DSL.table("Producto")).where(DSL.field("id").eq(id)).fetch();
        return exportarDatos(resultados);
    }

    // Método para obtener un producto en la base de datos por nombre
    public static String[][] obtenerProductoPorNombre(DSLContext query, String nombre) {
        Result resultados = query.select().from(DSL.table("Producto")).where(DSL.field("nombre").eq(nombre)).fetch();
        return exportarDatos(resultados);
    }

    // Método para obtener un producto en la base de datos por marca
    public static String[][] obtenerProductoPorMarca(DSLContext query, Marca marca) {
        Result resultados = query.select().from(DSL.table("Producto")).where(DSL.field("marca").eq(marca.getNombreMarca())).fetch();
        return exportarDatos(resultados);
    }

    // Método para obtener un producto en la base de datos por categoría
    public static String[][] obtenerProductoPorCategoria(DSLContext query, Categoria categoria) {
        Result resultados = query.select().from(DSL.table("Producto")).where(DSL.field("categoria").eq(categoria.getNombreCategoria())).fetch();
        return exportarDatos(resultados);
    }

    // Método para exportar los datos de la base de datos
    public static String[][] exportarDatos(Result resultados) {
        String[][] datosResultado = new String[resultados.size()][6];
        for (int registro = 0; registro < resultados.size(); registro++) {
            datosResultado[registro][0] = String.valueOf(resultados.getValue(registro, "id"));
            datosResultado[registro][1] = (String) resultados.getValue(registro, "nombre");
            datosResultado[registro][2] = String.valueOf(resultados.getValue(registro, "precio"));
            datosResultado[registro][3] = String.valueOf(resultados.getValue(registro, "stock"));
            datosResultado[registro][4] = (String) resultados.getValue(registro, "marca");
            datosResultado[registro][5] = (String) resultados.getValue(registro, "categoria");
        }
        return datosResultado;
    }

    // Método para obtener todos los productos en la base de datos
    public static String[][] obtenerProductos(DSLContext query) {
        Result resultados = query.select().from(DSL.table("Producto")).fetch();
        return exportarDatos(resultados);
    }
}
