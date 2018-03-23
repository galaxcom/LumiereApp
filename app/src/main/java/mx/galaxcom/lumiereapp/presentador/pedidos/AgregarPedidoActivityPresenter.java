package mx.galaxcom.lumiereapp.presentador.pedidos;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ListaClientesAdapter;
import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.Cliente;
import mx.galaxcom.lumiereapp.vista.pedidos.AgregarPedidoActivityView;
import mx.galaxcom.lumiereapp.vista.pedidos.ConfigurarPedidoFragmentView;

/**
 * Created by david on 09/03/2018.
 */

public class AgregarPedidoActivityPresenter implements IAgregarPedidoActivityPresenter {
    Context context;
    Activity activity;
    ConstructorBaseDatos constructor;
    AgregarPedidoActivityView vista;
    ArrayList<Cliente> items;
    List<String> grupos = new ArrayList<>();
    HashMap<String, List<String>> mapaItems;
    ListaClientesAdapter adapter;

    public AgregarPedidoActivityPresenter(Context context, Activity activity, AgregarPedidoActivityView vista){
        this.context = context;
        this.activity = activity;
        this.vista = vista;

        obtenerClientesBaseDatos();
    }

    @Override
    public void obtenerClientesBaseDatos() {
        constructor = new ConstructorBaseDatos(context);

        items = constructor.obtenerClientes();
    }

}
