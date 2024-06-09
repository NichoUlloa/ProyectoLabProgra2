package controller;

import model.Categoria;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.CategoriaDAO;
import org.jooq.DSLContext;
public class CategoriaController {
    private static final String nombreDataBase = "GestionInventarioDB";
    // los atribos de nuestra clase categoria son nombreCategoria, idCategoria
    // metodo registrarCategoria validando su existencia
    public static boolean registrarCategoria(String nombreCategoria, int idCategoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if (!CategoriaDAO.validarExistenciaCategoria(query, "idCategoria", idCategoria)) {
            Categoria categoria = new Categoria(nombreCategoria, idCategoria);
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

    public static boolean existeCategoria(int id) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        boolean existe = CategoriaDAO.validarExistenciaCategoria(query, "idCategoria", id);
        DBConnector.closeConnection();
        return existe;
    }

}

