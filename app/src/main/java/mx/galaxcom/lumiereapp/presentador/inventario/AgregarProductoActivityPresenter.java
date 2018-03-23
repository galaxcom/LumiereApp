package mx.galaxcom.lumiereapp.presentador.inventario;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ListaAgregarProductoAdapter;
import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.vista.inventario.AgregarProductoActivityView;

/**
 * Created by david on 26/02/2018.
 */

public class AgregarProductoActivityPresenter implements IAgregarProductoActivityPresenter {
    ConstructorBaseDatos constructorBaseDatos;
    AgregarProductoActivityView vista;
    Context context;
    Activity activity;
    ArrayList<ProductoInventario> items;
    List<String> grupos = new ArrayList<>();
    HashMap<String, List<String>> mapaItems;
    ListaAgregarProductoAdapter adapter;

    public AgregarProductoActivityPresenter(AgregarProductoActivityView vista, Context context, Activity activity) {
        this.vista = vista;
        this.context = context;
        this.activity = activity;

        obtenerProductosBaseDatos();
        obtenerMapaItemsBaseDatos();
    }


    @Override
    public void obtenerProductosBaseDatos() {
        constructorBaseDatos = new ConstructorBaseDatos(context);
        //items = constructorBaseDatos.obtenerProductosInventario();

    }

    @Override
    public void mostrarItemsLV() {
        vista.inicializarAdapter(vista.crearAdapter(grupos, mapaItems, context));
    }

    @Override
    public void obtenerMapaItemsBaseDatos() {
        grupos.add("Seleccionar Producto");
        mapaItems = constructorBaseDatos.obtenerProductosInventarioNecesariosMapa(grupos, activity);

        mostrarItemsLV();
    }

    @Override
    public void actualizarInventario(String productoSeleccionado, int cantidadAgregar) {
        adapter = vista.crearAdapter(grupos, mapaItems, context);
        //cantidadAgregar = vista.obtenerCantidadET();
        //productoSeleccionado = adapter.getGroup(0).toString();

        constructorBaseDatos.actualizarCantidadProducto(productoSeleccionado, cantidadAgregar);
    }

    @Override
    public void itemOnClick(int groupPosition, int childPosition) {
        ListaAgregarProductoAdapter adapter = vista.crearAdapter(grupos, mapaItems, context);
        vista.inicializarAdapter(adapter);

        String itemSeleccionado = (String) adapter.getChild(groupPosition, childPosition);

        grupos.set(0, itemSeleccionado);
        mapaItems = constructorBaseDatos.obtenerProductosInventarioNecesariosMapa(grupos, activity);
        adapter.setListaGrupos(grupos);
        adapter.setStringListHashMap(mapaItems);
    }

    /*public Bundle enviarBundleProductosLV(){
        String subCategoria = vista.obtenerSubCategoriaProductosLV();

        Cursor cursor = constructorBaseDatos.obtenerCursorProductosLV(subCategoria);
        Bundle bundle = new Bundle();

        cursor.setExtras(bundle);

        return bundle;
    }*/

}
