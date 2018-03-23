package mx.galaxcom.lumiereapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by david on 20/02/2018.
 */

public class ProductoInventario{
    private int id;
    private String categoria;
    private String subCategoria;
    private String nombre;
    private int cantidad;
    private float precioVenta;

    public ProductoInventario(String categoria, String subCategoria, String nombre, int cantidad, float precioVenta){
        this.categoria = categoria;
        this.subCategoria = subCategoria;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public ProductoInventario(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    
}
