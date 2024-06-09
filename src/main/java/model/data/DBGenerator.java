package model.data;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {
    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaProducto(create);
        crearTablaMarca(create);
        crearTablaCategoria(create);
        relacionarTabla(create, "producto", "id", "categoria");
        DBConnector.closeConnection();
    }

    public static DSLContext conectarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearTablaProducto(DSLContext create) {
        create.createTableIfNotExists("Producto")
                .column("id", INTEGER.identity(true))
                .column("nombre", VARCHAR(100))
                .column("precio", DOUBLE)
                .column("stock", INTEGER)
                .column("marca", VARCHAR(50))
                .column("categoria", INTEGER)
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaMarca(DSLContext create) {
        create.createTableIfNotExists("Marca")
                .column("idMarca", INTEGER.identity(true))
                .column("nombreMarca", VARCHAR(50))
                .constraint(primaryKey("idMarca"))
                .execute();
    }

    private static void crearTablaCategoria(DSLContext create) {
        create.createTableIfNotExists("Categoria")
                .column("idCategoria", INTEGER.identity(true))
                .column("nombreCategoria", VARCHAR(50))
                .constraint(primaryKey("idCategoria"))
                .execute();
    }

    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }
}
