package mx.galaxcom.lumiereapp.activities.pedidos;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.ListaProductosAgregadosAdapter;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.presentador.pedidos.ListaProductosPedidoFragmentPresenter;
import mx.galaxcom.lumiereapp.vista.pedidos.ListaProductosPedidoFragmentView;

public class ListaProductosPedidoFragment extends android.support.v4.app.Fragment implements ListaProductosPedidoFragmentView{
    ListView listaProductosAgregados;
    ListaProductosPedidoFragmentPresenter presenter;

    private IFragmentCommunication fragmentCommunication;

    public ListaProductosPedidoFragment() {
        // Required empty public constructor
    }

    public static ListaProductosPedidoFragment newInstance() {
        ListaProductosPedidoFragment fragment = new ListaProductosPedidoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_productos_pedido, container, false);

        listaProductosAgregados = view.findViewById(R.id.lvProductosAgregadosPedido);

        presenter = new ListaProductosPedidoFragmentPresenter(getActivity(), getContext(), this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaProductosAgregados = view.findViewById(R.id.lvProductosAgregadosPedido);

        presenter = new ListaProductosPedidoFragmentPresenter(getActivity(), getContext(), this);

        //int numProductosAgregados = listaProductosAgregados.getChildCount();

        //fragmentCommunication.getProductosCount();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentCommunication) {
            fragmentCommunication = (IFragmentCommunication) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentCommunication");
        }
    }

    @Override
    public void onDetach() {
        fragmentCommunication = null;
        super.onDetach();
    }

    @Override
    public ListaProductosAgregadosAdapter crearAdapter(Activity activity, Context context, ListaProductosPedidoFragmentView vista, ArrayList<ProductoInventario> productosAgregados) {
        ListaProductosAgregadosAdapter adapter = new ListaProductosAgregadosAdapter(activity, context, this, productosAgregados);
        return adapter;
    }

    @Override
    public void inicializarAdapter(ListaProductosAgregadosAdapter adapter) {
        listaProductosAgregados.setAdapter(adapter);
    }

    @Override
    public int obtenerNumeroItemsLV() {
        return listaProductosAgregados.getCount();
    }

    @Override
    public HashMap<String, Integer> obtenerCantidadesProductos() {
        HashMap<String, Integer> productosSeleccionados = new HashMap<>();

        for (int i = 0; i < listaProductosAgregados.getCount(); i++){
            ProductoInventario producto = (ProductoInventario) listaProductosAgregados.getAdapter().getItem(i);

            productosSeleccionados.put(producto.getNombre(), producto.getCantidad());
        }

        return productosSeleccionados;
    }

}
