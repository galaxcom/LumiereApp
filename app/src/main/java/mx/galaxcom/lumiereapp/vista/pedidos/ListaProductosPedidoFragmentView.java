package mx.galaxcom.lumiereapp.vista.pedidos;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import mx.galaxcom.lumiereapp.adapters.ListaProductosAgregadosAdapter;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;

/**
 * Created by david on 14/03/2018.
 */

public interface ListaProductosPedidoFragmentView {
    public ListaProductosAgregadosAdapter crearAdapter(Activity activity, Context context, ListaProductosPedidoFragmentView vista,
                                                       ArrayList<ProductoInventario> productosAgregados);

    public void inicializarAdapter(ListaProductosAgregadosAdapter adapter);

    public int obtenerNumeroItemsLV();

    public HashMap<String, Integer> obtenerCantidadesProductos();
}
