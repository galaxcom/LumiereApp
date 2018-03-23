package mx.galaxcom.lumiereapp.presentador.pedidos;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ListaClientesAdapter;
import mx.galaxcom.lumiereapp.adapters.ListaProductosAgregadosAdapter;
import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.Cliente;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.vista.pedidos.ListaProductosPedidoFragmentView;

/**
 * Created by david on 14/03/2018.
 */

public class ListaProductosPedidoFragmentPresenter implements IListaProductosPedidoFragmentPresenter {
    Context context;
    Activity activity;
    ConstructorBaseDatos constructor;
    ListaProductosPedidoFragmentView vista;
    ArrayList<ProductoInventario> items;
    ListaProductosAgregadosAdapter adapter;

    public ListaProductosPedidoFragmentPresenter(Activity activity, Context context, ListaProductosPedidoFragmentView vista){
        this.activity = activity;
        this.context = context;
        this.vista = vista;

        obtenerProductosBaseDatos();
    }

    @Override
    public void obtenerProductosBaseDatos() {
        constructor = new ConstructorBaseDatos(context);

        items = constructor.obtenerProductosAgregados(activity);

        mostrarItemsLV();
    }

    @Override
    public void mostrarItemsLV() {
        vista.inicializarAdapter(vista.crearAdapter(activity, context, vista, items));
    }
}
