package learn.mode.appventa.model;

public class Categoria {
    int idcategoria;
    String nom_producto;

    public Categoria(int idcategoria, String nom_producto) {
        this.idcategoria = idcategoria;
        this.nom_producto = nom_producto;
    }

    public Categoria() {
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }
}
