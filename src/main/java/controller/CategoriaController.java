package controller;

import model.Categoria;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.CategoriaDAO;
import org.jooq.DSLContext;

public class CategoriaController {
    private static final String nombreDataBase = "GestionInventarioDB";

    public static boolean registrarCategoria(String nombreCategoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (!CategoriaDAO.validarExistenciaCategoria(query, nombreCategoria)) {
            Categoria categoria = new Categoria(nombreCategoria);
            CategoriaDAO.registrarCategoria(query, categoria);
            DBConnector.closeConnection();
            return true;
        } else {
            DBConnector.closeConnection();
            return false;
        }
    }

    public static Object[] getIDCategorias() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        Object[] categorias = CategoriaDAO.getIDCategorias(query);
        DBConnector.closeConnection();
        return categorias;
    }
}
