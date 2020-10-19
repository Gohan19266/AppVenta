package learn.mode.appventa.model;

public class ProductoShop {
    private int id;
    private String nameproducto;
    private int precioproducto;
    private int cantidadproducto;
    private int idcategoria;
    private int idproducto;
    private int idusuario;
    private int totalproducto;

    public int getTotalproducto() {
        return totalproducto;
    }

    public void setTotalproducto(int totalproducto) {
        this.totalproducto = totalproducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameproducto() {
        return nameproducto;
    }

    public void setNameproducto(String nameproducto) {
        this.nameproducto = nameproducto;
    }

    public int getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(int precioproducto) {
        this.precioproducto = precioproducto;
    }

    public int getCantidadproducto() {
        return cantidadproducto;
    }

    public void setCantidadproducto(int cantidadproducto) {
        this.cantidadproducto = cantidadproducto;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}
