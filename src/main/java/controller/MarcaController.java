package controller;

import model.Marca;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.MarcaDAO;
import org.jooq.DSLContext;
public class MarcaController {
    private static final String nombreDataBase = "GestionInventarioDB";
    // los atribos de nuestra clase marca son nombreMarca, idMarca
    // metodo registrarMarca validando su existencia
    public static boolean registrarMarca(String nombreMarca, int idMarca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (!MarcaDAO.validarExistenciaMarca(query, "idMarca", idMarca)) {
            Marca marca = new Marca(nombreMarca, idMarca);
            MarcaDAO.registrarMarca(query, marca);
            DBConnector.closeConnection();
            return true;
        } else {
            DBConnector.closeConnection();
            return false;
        }
    }

    public static Object[] getIDMarcas() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        Object[] marcas = MarcaDAO.getIDMarcas(query);
        DBConnector.closeConnection();
        return marcas;
    }

    public static boolean existeMarca(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        boolean existe = MarcaDAO.validarExistenciaMarca(query, "idMarca", id);
        DBConnector.closeConnection();
        return existe;
    }

}
