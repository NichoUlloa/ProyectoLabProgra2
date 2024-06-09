package model.data;
import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;
import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {
    // metodo inicial para crear una base de datos y sus respectivas tablas en caso de que no exista
    // ** este apartado es necesario modificarlo
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

    //Metodo para conectarse a una base de datos ya creada
    public static DSLContext conectarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombreBD,"root","");
        DSLContext create = DSL.using(connection);
        return create;
    }
    //Crea una base de datos en caso de que no exista
    private static void crearBaseDato(DSLContext create, String nombreBD){
        create.createDatabaseIfNotExists(nombreBD).execute();
    }
    //Actualiza la conexion inicial para conectar a la base de datos
    private static DSLContext actualizarConexion(Connection connection,String nombreBD){
        DBConnector.closeConnection();
        connection= DBConnector.connection(nombreBD,"root","");
        DSLContext create=DSL.using(connection);
        return create;
    }

    //Crea la tabla producto id, nombre, precio, stock, marca(enum), categoria(enum)
    private static void crearTablaProducto(DSLContext create){
        create.createTableIfNotExists("Producto").column("id",INTEGER)
                .column("nombre",VARCHAR(100))
                .column("precio",DOUBLE)
                .column("stock",INTEGER)
                .column("marca",VARCHAR(50))
                .column("categoria",INTEGER)
                .constraint(primaryKey("id")).execute();
    }

    //Crea la tabla marca idMarca, nombreMarca
    private static void crearTablaMarca(DSLContext create){
        create.createTableIfNotExists("Marca").column("idMarca",INTEGER)
                .column("nombreMarca",VARCHAR(50))
                .constraint(primaryKey("idMarca")).execute();
    }

    //Crea la tabla categoria idCategoria, nombreCategoria
    private static void crearTablaCategoria(DSLContext create){
        create.createTableIfNotExists("Categoria").column("idCategoria",INTEGER)
                .column("nombreCategoria",VARCHAR(50))
                .constraint(primaryKey("idCategoria")).execute();
    }

    // relaciona dos tablas a traves de una clave foranea, usar solo si se relacionan tablas
    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }

    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType tipoColumna) {
        create.alterTableIfExists(nombreTabla).addColumn(columna, tipoColumna);
    }
}