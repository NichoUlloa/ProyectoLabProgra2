package model.data.dao;

import model.Marca;
import model.data.DBConnector;
import model.data.DBGenerator;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import java.util.ArrayList;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
public class MarcaDAO {
    // metodo para agregar una marca a la base de datos nombreMarca, idMarca
    public static void registrarMarca(DSLContext query, Marca marca){
        Table tablaMarca= table(name("Marca"));
        Field[] columnas = tablaMarca.fields("nombreMarca","idMarca");
        query.insertInto(tablaMarca, columnas[0], columnas[1])
                .values(marca.getNombreMarca(),marca.getIdMarca())
                .execute();
    }

    // metodo para validar si una marca ya existe en la base de datos
    public static boolean validarExistenciaMarca(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Marca")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }

    // metodo para modificar una marca en la base de datos
    public void modificarMarca(DSLContext query, Marca   nombreMarca){
        query.update(DSL.table("Marca")).set(DSL.field("nombreMarca"),nombreMarca)
                .where(DSL.field("nombreMarca").eq(nombreMarca)).execute();
    }


    // metodo para eliminar una marca en la base de datos
    public void eliminarMarca(DSLContext query, String nombreMarca){
        Table tablaMarca= table(name("Marca"));
        query.delete(DSL.table("Marca")).where(DSL.field("nombreMarca").eq(nombreMarca)).execute();
    }

    // metodo para obtener una marca en la base de datos
    public List obtenerMarca(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Marca")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaMarcas(resultados);
    }

    // metodo para obtener una lista de todas las marcas en la base de datos nombreMarca, idMarca
    private List obtenerListaMarcas(Result resultados){
        List<Marca> marcas= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String nombreMarca = (String) resultados.getValue(fila,"nombreMarca");
            int idMarca = (int) resultados.getValue(fila,"idMarca");
            marcas.add(new Marca(nombreMarca,idMarca));
        }
        return marcas;
    }

    // metodo para exportar los datos de la base de datos  nombreMarca, idMarca
    public static String[][] exportarDatos(Result resultados){
        String[][] datosResultado=new String[resultados.size()][2];
        for(int registro = 0; registro < resultados.size(); registro ++){
            datosResultado[registro][0] = (String) resultados.getValue(registro,"nombreMarca");
            datosResultado[registro][1] = String.valueOf(resultados.getValue(registro,"idMarca"));
        }
        return datosResultado;
    }

    // metodo para obtener todos las marcas en la base de datos nombreMarca, idMarca
    public static String[][] obtenerMarcas(DSLContext query){
        Result resultados = query.select().from(DSL.table("Marca")).fetch();
        return exportarDatos(resultados);
    }

    // metodo para obtener una marca en la base de datos por id
    public static String[][] obtenerMarcaPorId(DSLContext query, int idMarca){
        Result resultados = query.select().from(DSL.table("Marca")).where(DSL.field("idMarca").eq(idMarca)).fetch();
        return exportarDatos(resultados);
    }

    // metodo para obtener una marca en la base de datos por nombre
    public static String[][] obtenerMarcaPorNombre(DSLContext query, String nombreMarca){
        Result resultados = query.select().from(DSL.table("Marca")).where(DSL.field("nombreMarca").eq(nombreMarca)).fetch();
        return exportarDatos(resultados);
    }

    // metodo getIDMarcas
    public static Object[] getIDMarcas(DSLContext query) {
        Table marca = DSL.table("Marca");
        Result resultados = query.select(marca.field("idMarca")).from(marca).fetch();
        if (resultados.size() == 0) {
            return new String[]{"Error no existen marcas"};
        } else {
            return resultados.getValues("idMarca").toArray();
        }
    }
}

// public static Object[] getCodigoCarreras(DSLContext query){
//        Table carrera= DSL.table("Carrera");
//        Result resultados = query.select(carrera.field("codigo")).from(carrera).fetch();
//        if(resultados.size()==0){
//            return new String[]{"Error no existen carreras"};
//        }
//        else {
//            return resultados.getValues("codigo").toArray();
//        }
//    }

// ejemplo
//  public static void agregarEstudiante(DSLContext query, Estudiante estudiante){
//        Table tablaEstudiante= table(name("Estudiante"));
//        Field[] columnas = tablaEstudiante.fields("rut","nombre","matricula","codigo_carrera");
//        query.insertInto(tablaEstudiante, columnas[0], columnas[1],columnas[2],columnas[3])
//                .values(estudiante.getRut(),estudiante.getNombre(),estudiante.getMatricula(),estudiante.getCarrera().getCodigoCarrera())
//                .execute();
//    }

// public static boolean validarExistenciaEstudiante(DSLContext query,String columnaTabla, Object dato){
//        Result resultados = query.select().from(DSL.table("Estudiante")).where(DSL.field(columnaTabla).eq(dato)).fetch();
//        if(resultados.size()>=1){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
// public void modificarEstudiante(DSLContext query, String rut, String columnaTabla, Object dato){
//        query.update(DSL.table("Estudiante")).set(DSL.field(columnaTabla),dato).
//                where(DSL.field("rut").eq(rut)).execute();
//    }
// public void eliminarEstudiante(DSLContext query, String rut){
//        Table tablaEstudiante= table(name("Estudiante"));
//        query.delete(DSL.table("Estudiante")).where(DSL.field("rut").eq(rut)).execute();
//    }
// public List obtenerEstudiante(DSLContext query, String columnaTabla, Object dato){
//        Result resultados = query.select().from(DSL.table("Estudiante")).where(DSL.field(columnaTabla).eq(dato)).fetch();
//        return obtenerListaEstudiantes(resultados);
//    }

// private List obtenerListaEstudiantes(Result resultados){
//        List<Estudiante> estudiantes= new ArrayList<>();
//        for(int fila=0; fila<resultados.size();fila++){
//            String rut = (String) resultados.getValue(fila,"rut");
//            String nombre = (String) resultados.getValue(fila,"nombre");
//            String matricula = (String) resultados.getValue(fila,"matricula");
//            estudiantes.add(new Estudiante(rut,nombre,matricula,null));
//        }
//        return estudiantes;
//    }

// public static String[][] obtenerEstudiantesCursoCodigo(DSLContext query, String codigo){
//        Table estudiante = DSL.table("Estudiante");
//        Table carrera = DSL.table("Carrera");
//        Result resultados = query.select().from(estudiante).join(carrera).on(DSL.field("codigo").eq(DSL.field("codigo_carrera")))
//                .where(DSL.field("codigo_carrera").eq(codigo)).fetch();
//        return exportardatos(resultados);
//    }

// private static String[][] exportardatos(Result resultados){
//        String[][] datosResultado=new String[resultados.size()][4];
//        for(int registro = 0; registro < resultados.size(); registro ++){
//            datosResultado[registro][0] = (String) resultados.getValue(registro,"nombre");
//            datosResultado[registro][1] = (String) resultados.getValue(registro,"matricula");
//            datosResultado[registro][2] = (String) resultados.getValue(registro,"nombre_carrera");
//            datosResultado[registro][3] = (String) resultados.getValue(registro,"codigo");
//
//        }
//        return datosResultado;
//    }