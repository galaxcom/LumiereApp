package mx.galaxcom.lumiereapp.vista.pedidos;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ListaClientesAdapter;

/**
 * Created by david on 07/03/2018.
 */

public interface ConfigurarPedidoFragmentView {
    public void inicializarAdapter(ListaClientesAdapter adapter);

    public ListaClientesAdapter crearAdapter(Context context, List<String> grupos, HashMap<String, List<String>> mapaItems);

    public void botonAgregarProductoOnclick();

    public void btnConfirmarPedidoOnclick();

    public void btnSelectDateOnClick(View v);

    public void setDateText(final Calendar calendario);

    public void configurarTextView(int itemsCount);

    public String obtenerClienteSeleccionado();

    public String obtenerFechaSeleccionada();

    public void setClienteSeleccionado(String clienteSeleccionado);

    public void setFechaSeleccionada(String fechaSeleccionada);
}
