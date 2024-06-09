package model.data.dao;

import model.Categoria;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class CategoriaDAO {

    public static void registrarCategoria(DSLContext query, Categoria categoria) {
        Table<?> tablaCategoria = table(name("Categoria"));
        Field<?>[] columnas = new Field<?>[]{DSL.field("nombreCategoria")}; // Solo incluir el nombre, idCategoria es autoincremental
        query.insertInto(tablaCategoria, columnas)
                .values(categoria.getNombreCategoria())
                .execute();
    }

    public static boolean validarExistenciaCategoria(DSLContext query, String nombreCategoria) {
        return query.fetchExists(DSL.table("Categoria"), DSL.field("nombreCategoria").eq(nombreCategoria));
    }

    // método getIDCategorias
    public static Object[] getIDCategorias(DSLContext query) {
        Table<?> categoria = DSL.table("Categoria");
        Result resultados = query.select(categoria.field("idCategoria")).from(categoria).fetch();
        if (resultados.size() == 0) {
            return new String[]{"Error no existen categorias"};
        } else {
            return resultados.getValues("idCategoria").toArray();
        }
    }

    // metodo getNombreCategoria
    public static String getNombreCategoria(DSLContext query, int idCategoria) {
        Result resultados = query.select().from(DSL.table("Categoria")).where(DSL.field("idCategoria").eq(idCategoria)).fetch();
        return (String) resultados.getValue(0, "nombreCategoria");
    }

    private static String[][] exportarDatos(Result resultados) {
        String[][] datosResultado = new String[resultados.size()][2];
        for (int registro = 0; registro < resultados.size(); registro++) {
            datosResultado[registro][0] = String.valueOf(resultados.getValue(registro, "idCategoria"));
            datosResultado[registro][1] = (String) resultados.getValue(registro, "nombreCategoria");
        }
        return datosResultado;
    }
}
