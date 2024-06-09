package model.data.dao;

import model.Categoria;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class CategoriaDAO {
    // método para agregar una categoría a la base de datos
    public static void registrarCategoria(DSLContext query, Categoria categoria) {
        Table tablaCategoria = table(name("Categoria"));
        Field[] columnas = tablaCategoria.fields("nombreCategoria", "idCategoria");
        query.insertInto(tablaCategoria, columnas[0], columnas[1])
                .values(categoria.getNombreCategoria(), categoria.getIdCategoria())
                .execute();
    }

    // método para validar si una categoría ya existe en la base de datos
    public static boolean validarExistenciaCategoria(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(DSL.table("Categoria")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return resultados.size() >= 1;
    }

    // método para modificar una categoría en la base de datos
    public void modificarCategoria(DSLContext query, String nombreCategoria, String columnaTabla, Object dato) {
        query.update(DSL.table("Categoria")).set(DSL.field(columnaTabla), dato)
                .where(DSL.field("nombreCategoria").eq(nombreCategoria)).execute();
    }

    // método para eliminar una categoría en la base de datos
    public void eliminarCategoria(DSLContext query, String nombreCategoria) {
        Table tablaCategoria = table(name("Categoria"));
        query.delete(DSL.table("Categoria")).where(DSL.field("nombreCategoria").eq(nombreCategoria)).execute();
    }

    // método para obtener una categoría en la base de datos
    public List<Categoria> obtenerCategoria(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(DSL.table("Categoria")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaCategorias(resultados);
    }

    // método para obtener una lista de todas las categorías en la base de datos
    private List<Categoria> obtenerListaCategorias(Result resultados) {
        List<Categoria> categorias = new ArrayList<>();
        for (int fila = 0; fila < resultados.size(); fila++) {
            String nombreCategoria = (String) resultados.getValue(fila, "nombreCategoria");
            int idCategoria = (int) resultados.getValue(fila, "idCategoria");
            categorias.add(new Categoria(nombreCategoria, idCategoria));
        }
        return categorias;
    }

    // método para exportar los datos de la base de datos
    public static String[][] exportarDatos(Result resultados) {
        String[][] datosResultado = new String[resultados.size()][2];
        for (int registro = 0; registro < resultados.size(); registro++) {
            datosResultado[registro][0] = (String) resultados.getValue(registro, "nombreCategoria");
            datosResultado[registro][1] = String.valueOf(resultados.getValue(registro, "idCategoria"));
        }
        return datosResultado;
    }

    // método para obtener todas las categorías en la base de datos
    public static String[][] obtenerCategorias(DSLContext query) {
        Result resultados = query.select().from(DSL.table("Categoria")).fetch();
        return exportarDatos(resultados);
    }

    // método para obtener una categoría en la base de datos por id
    public static String[][] obtenerCategoriaPorId(DSLContext query, int idCategoria) {
        Result resultados = query.select().from(DSL.table("Categoria")).where(DSL.field("idCategoria").eq(idCategoria)).fetch();
        return exportarDatos(resultados);
    }

    // método para obtener una categoría en la base de datos por nombre
    public static String[][] obtenerCategoriaPorNombre(DSLContext query, String nombreCategoria) {
        Result resultados = query.select().from(DSL.table("Categoria")).where(DSL.field("nombreCategoria").eq(nombreCategoria)).fetch();
        return exportarDatos(resultados);
    }

    // metodo getIDCategorias
    public static Object[] getIDCategorias(DSLContext query) {
        Table categoria = DSL.table("Categoria");
        Result resultados = query.select(categoria.field("idCategoria")).from(categoria).fetch();
        if (resultados.size() == 0) {
            return new String[]{"Error no existen categorias"};
        } else {
            return resultados.getValues("idCategoria").toArray();
        }
    }
}

// ejemplo
//  // metodo getIDMarcas
//    public static Object[] getIDMarcas(DSLContext query) {
//        Table marca = DSL.table("Marca");
//        Result resultados = query.select(marca.field("idMarca")).from(marca).fetch();
//        if (resultados.size() == 0) {
//            return new String[]{"Error no existen marcas"};
//        } else {
//            return resultados.getValues("idMarca").toArray();
//        }
//    }
