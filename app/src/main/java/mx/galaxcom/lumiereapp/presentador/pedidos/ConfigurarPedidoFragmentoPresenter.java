package mx.galaxcom.lumiereapp.presentador.pedidos;

import android.app.Activity;
import android.content.Context;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.activities.pedidos.ConfigurarPedidoFragment;
import mx.galaxcom.lumiereapp.adapters.ListaClientesAdapter;
import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.Cliente;
import mx.galaxcom.lumiereapp.vista.pedidos.ConfigurarPedidoFragmentView;

/**
 * Created by david on 07/03/2018.
 */

public class ConfigurarPedidoFragmentoPresenter implements IConfigurarPedidoFragmentPresenter {
    Context context;
    Activity activity;
    ConstructorBaseDatos constructor;
    ConfigurarPedidoFragmentView vista;
    ArrayList<Cliente> items;
    List<String> grupos = new ArrayList<>();
    HashMap<String, List<String>> mapaItems;
    ListaClientesAdapter adapter;

    public ConfigurarPedidoFragmentoPresenter(Context context, Activity activity, ConfigurarPedidoFragmentView vista){
        this.context = context;
        this.activity = activity;
        this.vista = vista;

        obtenerClientesBaseDatos();
        obtenerMapaItemsBaseDatos();
    }

    public ConfigurarPedidoFragmentoPresenter(Context context, Activity activity, ConfigurarPedidoFragmentView vista, List<String> grupos){
        this.context = context;
        this.activity = activity;
        this.vista = vista;
        this.grupos = grupos;

        obtenerClientesBaseDatos();
        obtenerMapaItemsBaseDatos();
    }

    @Override
    public void obtenerClientesBaseDatos() {
        constructor = new ConstructorBaseDatos(context);

        items = constructor.obtenerClientes();
    }

    @Override
    public void mostrarClientesLV() {
        vista.inicializarAdapter(vista.crearAdapter(context, grupos, mapaItems));
    }

    @Override
    public void obtenerMapaItemsBaseDatos() {
        if (grupos.size() != 0) {
            mapaItems = constructor.obtenerMapaClientes(grupos);

            mostrarClientesLV();
        }else{
            grupos.add("Seleccionar Cliente");
            mapaItems = constructor.obtenerMapaClientes(grupos);

            mostrarClientesLV();

            Toast.makeText(context, "grupos.size() == 0", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemOnClick(int groupPosition, int childPosition) {
        ListaClientesAdapter adapter = vista.crearAdapter(context, grupos, mapaItems);
        vista.inicializarAdapter(adapter);

        String itemSeleccionado = (String) adapter.getChild(groupPosition, childPosition);

        grupos.set(0, itemSeleccionado);
        mapaItems = constructor.obtenerMapaClientes(grupos);
        adapter.setListaGrupos(grupos);
        adapter.setHashMapItems(mapaItems);
    }

    @Override
    public void actualizarInventario(HashMap<String, Integer> productosAgregados) {
        constructor.actualizarInventarioPedido(productosAgregados);
    }

    public ConstructorBaseDatos getConstructor() {
        return constructor;
    }

    public void setConstructor(ConstructorBaseDatos constructor) {
        this.constructor = constructor;
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
        ListaClientesAdapter adapter = vista.crearAdapter(context, grupos, mapaItems);
        vista.inicializarAdapter(adapter);

        this.grupos.set(0, grupos.get(0));
        mapaItems = constructor.obtenerMapaClientes(grupos);
        adapter.setListaGrupos(grupos);
        adapter.setHashMapItems(mapaItems);
        adapter.notifyDataSetChanged();
    }
}
