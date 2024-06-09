package controller;

import model.Marca;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.MarcaDAO;
import org.jooq.DSLContext;

public class MarcaController {
    private static final String nombreDataBase = "GestionInventarioDB";

    public static boolean registrarMarca(String nombreMarca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (!MarcaDAO.validarExistenciaMarca(query, nombreMarca)) {
            Marca marca = new Marca(nombreMarca);
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
}
