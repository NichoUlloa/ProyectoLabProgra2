package model;

public class Marca {
    // atributos: nombreMarca, idMarca
    private String nombreMarca;
    private int idMarca;

    // constructores
    public Marca() {
    }

    public Marca(String nombreMarca, int idMarca) {
        this.nombreMarca = nombreMarca;
        this.idMarca = idMarca;
    }

    // getters y setters
    public String getNombreMarca() {
        return nombreMarca;
    }
    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public int getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    // toString
    @Override
    public String toString() {
        return "Marca{" +
                "nombreMarca='" + nombreMarca + '\'' +
                ", idMarca=" + idMarca +
                '}';
    }
}
