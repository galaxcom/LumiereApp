package mx.galaxcom.lumiereapp.vista.pedidos;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ListaAgregarProductoAdapter;
import mx.galaxcom.lumiereapp.adapters.ListaProductoAgregarPedidoAdapter;
import mx.galaxcom.lumiereapp.adapters.ProductoAgregarPedidoERVAdapter;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;

/**
 * Created by david on 13/03/2018.
 */

public interface ListadoProductosAgregarPedidoActivityView {
    public void inicializarAdapter(ListaProductoAgregarPedidoAdapter adapter);

    public ListaProductoAgregarPedidoAdapter crearAdapter(Context context, List<String> listaGrupos, HashMap<String, List<String>> hashMapItems);

    public void inicializarAdapterRV(ProductoAgregarPedidoERVAdapter adapter);

    public ProductoAgregarPedidoERVAdapter crearAdapterRV(List<CategoriasProductoERV> grupos);

    public void inicializarLayoutManager();

    public ArrayList<String> obtenerProductosSeleccionados();

    public ArrayList<String> obtenerCantidadesProductosSeleccionados();

    public ArrayList<Integer> obtenerPrecioUnitarioProductosSeleccionados();

    public void setBtnContinuarClickable();

    public void btnContinuarOnClick(View v);
}
