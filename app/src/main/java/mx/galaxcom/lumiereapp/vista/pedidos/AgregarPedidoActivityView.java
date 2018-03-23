package mx.galaxcom.lumiereapp.vista.pedidos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.AgregarPedidoPagerAdapter;
import mx.galaxcom.lumiereapp.adapters.ListaClientesAdapter;

/**
 * Created by david on 07/03/2018.
 */

public interface AgregarPedidoActivityView {

    public void inicializarTransaccion();

    public void inicializarAdapterViewPager(AgregarPedidoPagerAdapter adapter);

    public AgregarPedidoPagerAdapter crearViewPagerAdapter(android.support.v4.app.FragmentManager fragmentManager, Context context);

    public void setTabLayoutIcons();

}
