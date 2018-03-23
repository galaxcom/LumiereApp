package mx.galaxcom.lumiereapp.presentador.pedidos;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;

import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.vista.pedidos.ListadoProductosAgregarPedidoActivityView;

/**
 * Created by david on 13/03/2018.
 */

public class ListadoProductosAgregarPedidoActivityPresenter implements IListadoProductosAgregarPedidoActivityPresenter{
    Context context;
    Activity activity;
    ConstructorBaseDatos constructor;
    ListadoProductosAgregarPedidoActivityView vista;
    ArrayList<ProductoInventario> items;
    List<String> grupos = new ArrayList<>();
    List<CategoriasProductoERV> categorias = new ArrayList<>();
    HashMap<String, List<String>> mapaItems;

    public ListadoProductosAgregarPedidoActivityPresenter(Context context, Activity activity, ListadoProductosAgregarPedidoActivityView vista){
        this.context = context;
        this.activity = activity;
        this.vista = vista;

        obtenerProductosBaseDatos();
        obtenerDatosRV();
    }

    @Override
    public void obtenerProductosBaseDatos() {
        constructor = new ConstructorBaseDatos(context);

        items = constructor.obtenerProductosInventario();
    }

    @Override
    public void mostrarItemsLV() {
        vista.inicializarAdapter(vista.crearAdapter(context, grupos, mapaItems));
    }

    @Override
    public void mostrarItemsRV() {
        vista.inicializarAdapterRV(vista.crearAdapterRV(categorias));
    }

    @Override
    public void obtenerMapaItemsBaseDatos() {
        ArrayList<CategoriaInventario> categoriasO = constructor.obtenerCategorias();

        for (int i = 0; i < categoriasO.size(); i++){
            String nombreCategoria = categoriasO.get(i).getNombreCategoria();

            grupos.add(nombreCategoria);
        }

        mapaItems = constructor.obtenerMapaProductos(grupos);

        mostrarItemsLV();
    }

    @Override
    public void obtenerDatosRV() {
        ArrayList<CategoriaInventario> categoriasInventario = constructor.obtenerCategorias();

        for (int i = 0; i < categoriasInventario.size(); i++){
            String nombreCategoria = categoriasInventario.get(i).getNombreCategoria();

            CategoriasProductoERV categoria = new CategoriasProductoERV(nombreCategoria,
                    constructor.obtenerProductosCategoria(nombreCategoria));

            categorias.add(categoria);
        }

        mostrarItemsRV();
    }
}
