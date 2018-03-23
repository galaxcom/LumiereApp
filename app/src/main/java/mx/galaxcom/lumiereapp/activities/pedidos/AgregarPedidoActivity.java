package mx.galaxcom.lumiereapp.activities.pedidos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.AgregarPedidoPagerAdapter;
import mx.galaxcom.lumiereapp.adapters.ListaProductosAgregadosAdapter;
import mx.galaxcom.lumiereapp.presentador.pedidos.AgregarPedidoActivityPresenter;
import mx.galaxcom.lumiereapp.vista.pedidos.AgregarPedidoActivityView;
import mx.galaxcom.lumiereapp.vista.pedidos.ListaProductosPedidoFragmentView;

public class AgregarPedidoActivity extends AppCompatActivity implements AgregarPedidoActivityView, DatePickerDialog.OnDateSetListener, IFragmentCommunication{
    //ExpandableListView listaClientes;
    AgregarPedidoActivityPresenter presenter;
    TextView txtFecha;
    ListView productosAgregados;
    TextView txtProductoAgregado;
    ImageView imgProductoAgregado;
    ViewPager viewPager;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_agregar_pedido);

            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            tabs = (TabLayout) findViewById(R.id.tabsAgregarPedido);
            tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_configurar_pedido));
            tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_listado_productos));
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);

            viewPager = (ViewPager) findViewById(R.id.viewPagerAgregarPedido);
            final AgregarPedidoPagerAdapter adapter = new AgregarPedidoPagerAdapter(getSupportFragmentManager(), this);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
            tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            //inicializarTransaccion();

            presenter = new AgregarPedidoActivityPresenter(this, this, this);

            txtFecha = findViewById(R.id.txtFecha);

            productosAgregados = findViewById(R.id.lvProductosAgregadosPedido);

            //obtenerDatosSeleccionados();


        }catch (NullPointerException e){

        }


    }


    @Override
    public void inicializarTransaccion() {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layoutActivityAgregarPedido, new ConfigurarPedidoFragment());
        ft.add(R.id.layoutActivityAgregarPedido, new ListaProductosPedidoFragment());
        ft.commit();
    }

    @Override
    public void inicializarAdapterViewPager(AgregarPedidoPagerAdapter adapter) {
        //viewPager.setAdapter(adapter);
    }

    @Override
    public AgregarPedidoPagerAdapter crearViewPagerAdapter(android.support.v4.app.FragmentManager fragmentManager, Context context) {
        AgregarPedidoPagerAdapter adapter = new AgregarPedidoPagerAdapter(fragmentManager, context);

        return adapter;
    }

    @Override
    public void setTabLayoutIcons() {
        //tabs.getTabAt(0).setIcon(R.drawable.ic_configurar_pedido);
        //tabs.getTabAt(1).setIcon(R.drawable.ic_listado_productos);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendario = new GregorianCalendar(i, i1, i2);

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        txtFecha = findViewById(R.id.txtFecha);

        txtFecha.setText(dateFormat.format(calendario.getTime()));
    }


    @Override
    public int getProductosCount() {
        AgregarPedidoPagerAdapter adapter = (AgregarPedidoPagerAdapter) viewPager.getAdapter();
        Fragment fragment = adapter.getFragment(1);

        return ((ListaProductosPedidoFragmentView)fragment).obtenerNumeroItemsLV();
    }

    @Override
    public void isLVEmpty() {
        AgregarPedidoPagerAdapter adapter = (AgregarPedidoPagerAdapter) viewPager.getAdapter();
        Fragment fragment = adapter.getFragment(0);

        ((ConfigurarPedidoFragment)fragment).configurarTextView(getProductosCount());
    }

    @Override
    public void obtenerDatosSeleccionados() {
        AgregarPedidoPagerAdapter adapter = (AgregarPedidoPagerAdapter) viewPager.getAdapter();
        Fragment fragment = adapter.getFragment(0);

        String clienteSeleccionado = getIntent().getStringExtra("cliente_seleccionado");

        String fechaSeleccionada = getIntent().getStringExtra("fecha_seleccionada");

        if (clienteSeleccionado != null && fragment != null){
            ((ConfigurarPedidoFragment)fragment).setClienteSeleccionado(clienteSeleccionado);
        }if (fechaSeleccionada != null && fragment != null){
            ((ConfigurarPedidoFragment)fragment).setFechaSeleccionada(fechaSeleccionada);
        }
    }

    @Override
    public HashMap<String, Integer> obtenerCantidadesProducto() {
        AgregarPedidoPagerAdapter adapter = (AgregarPedidoPagerAdapter) viewPager.getAdapter();
        Fragment fragment = adapter.getFragment(1);

        return ((ListaProductosPedidoFragment)fragment).obtenerCantidadesProductos();
    }
}
