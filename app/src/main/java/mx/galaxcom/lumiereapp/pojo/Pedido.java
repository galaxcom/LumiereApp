package mx.galaxcom.lumiereapp.pojo;

import java.util.ArrayList;

/**
 * Created by david on 15/03/2018.
 */

public class Pedido {
    private int id;
    private String cliente;
    private String fecha;
    private ArrayList<ProductoInventario> productos;

    public Pedido(String cliente, String fecha, ArrayList<ProductoInventario> productos){
        this.cliente = cliente;
        this.fecha = fecha;
        this.productos = productos;
    }

    public Pedido(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<ProductoInventario> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoInventario> productos) {
        this.productos = productos;
    }
}
