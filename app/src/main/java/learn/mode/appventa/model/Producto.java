package learn.mode.appventa.model;

import android.widget.EditText;

public class Producto {
    private int idproducto;
    private String nom_producto;
    private int precio;
    private int cantidad;
    private int idcategoria;

    public Producto(int idproducto, String nom_producto, int precio, int cantidad, int idcategoria) {
        this.idproducto = idproducto;
        this.nom_producto = nom_producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idcategoria = idcategoria;
    }

    public Producto() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }
}
