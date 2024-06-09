package model;

public class Categoria {
    // atributos: nombreCategoria, idCategoria
    private String nombreCategoria;
    private int idCategoria;

    // constructores
    public Categoria() {
    }

    public Categoria(String nombreCategoria, int idCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.idCategoria = idCategoria;
    }

    // getters y setters
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // toString
    @Override
    public String toString() {
        return "Categoria{" +
                "nombreCategoria='" + nombreCategoria + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
