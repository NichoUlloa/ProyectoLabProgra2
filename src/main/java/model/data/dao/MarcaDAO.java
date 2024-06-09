package model.data.dao;

import model.Marca;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class MarcaDAO {

    public static void registrarMarca(DSLContext query, Marca marca) {
        Table<?> tablaMarca = table(name("Marca"));
        Field<?>[] columnas = new Field<?>[]{DSL.field("nombreMarca")}; // Solo incluir el nombre, idMarca es autoincremental
        query.insertInto(tablaMarca, columnas)
                .values(marca.getNombreMarca())
                .execute();
    }

    public static boolean validarExistenciaMarca(DSLContext query, String nombreMarca) {
        return query.fetchExists(DSL.table("Marca"), DSL.field("nombreMarca").eq(nombreMarca));
    }

    // método para obtener todas las marcas en la base de datos
    public static String[][] obtenerMarcas(DSLContext query) {
        Result resultados = query.select().from(DSL.table("Marca")).fetch();
        return exportarDatos(resultados);
    }

    // método getIDMarcas
    public static Object[] getIDMarcas(DSLContext query) {
        Table<?> marca = DSL.table("Marca");
        Result resultados = query.select(marca.field("idMarca")).from(marca).fetch();
        if (resultados.size() == 0) {
            return new String[]{"Error no existen marcas"};
        } else {
            return resultados.getValues("idMarca").toArray();
        }
    }

    private static String[][] exportarDatos(Result resultados) {
        String[][] datosResultado = new String[resultados.size()][2];
        for (int registro = 0; registro < resultados.size(); registro++) {
            datosResultado[registro][0] = String.valueOf(resultados.getValue(registro, "idMarca"));
            datosResultado[registro][1] = (String) resultados.getValue(registro, "nombreMarca");
        }
        return datosResultado;
    }
}
