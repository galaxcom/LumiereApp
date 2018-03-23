package mx.galaxcom.lumiereapp.vista.inventario;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ListaAgregarProductoAdapter;
import mx.galaxcom.lumiereapp.adapters.ProductoInventarioAdapter;

/**
 * Created by david on 26/02/2018.
 */

public interface AgregarProductoActivityView {
    public ListaAgregarProductoAdapter crearAdapter(List<String> grupos, HashMap<String, List<String>> mapa, Context context);

    public void inicializarAdapter(ListaAgregarProductoAdapter adapter);

    public String obtenerCantidadET();

    public void btnAgregarOnClick(View v);

    public String obtenerProductoLV();

    public String obtenerProductoSeleccionado();
}
