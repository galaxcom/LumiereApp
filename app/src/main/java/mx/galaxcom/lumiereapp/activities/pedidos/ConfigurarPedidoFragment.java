package mx.galaxcom.lumiereapp.activities.pedidos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.ListaClientesAdapter;
import mx.galaxcom.lumiereapp.presentador.pedidos.ConfigurarPedidoFragmentoPresenter;
import mx.galaxcom.lumiereapp.vista.pedidos.ConfigurarPedidoFragmentView;

public class ConfigurarPedidoFragment extends android.support.v4.app.Fragment implements ConfigurarPedidoFragmentView, DatePickerDialog.OnDateSetListener{
    private ConfigurarPedidoFragmentoPresenter presenter;
    private ExpandableListView listaClientes;
    private Button botonFecha;
    private Button botonAgregarProducto;
    private Button botonConfirmarPedido;
    private TextView txtFecha;
    private TextView txtProductoAgregado;
    private ImageView imgProductoAgregado;
    private IFragmentCommunication fragmentCommunication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configurar_pedido, container, false);

        listaClientes = view.findViewById(R.id.expLVClientes);

        presenter = new ConfigurarPedidoFragmentoPresenter(getActivity(), getActivity(), this);

        txtFecha = view.findViewById(R.id.txtFecha);

        fragmentCommunication.obtenerDatosSeleccionados();

        listaClientes.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                presenter.itemOnClick(i, i1);

                return true;
            }
        });

        botonFecha = view.findViewById(R.id.btnSeleccionarFecha);

        botonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSelectDateOnClick(view);
            }
        });

        botonAgregarProducto = view.findViewById(R.id.btnAgregarProductoPedido);

        botonAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonAgregarProductoOnclick();
            }
        });

        txtProductoAgregado = view.findViewById(R.id.txtProductoAgregado);
        imgProductoAgregado = view.findViewById(R.id.imgProductoAgregado);

        String texto = getActivity().getIntent().getStringExtra("texto");
        if (texto != null) {
            txtProductoAgregado.setText(texto);
            imgProductoAgregado.setImageResource(R.drawable.icons8_checked_96);
        }

        botonConfirmarPedido = view.findViewById(R.id.btnConfirmarPedido);

        botonConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnConfirmarPedidoOnclick();
            }
        });

        return view;
    }

    @Override
    public void inicializarAdapter(ListaClientesAdapter adapter) {
        listaClientes.setAdapter(adapter);
    }

    @Override
    public ListaClientesAdapter crearAdapter(Context context, List<String> grupos, HashMap<String, List<String>> mapaItems) {
        ListaClientesAdapter adapter = new ListaClientesAdapter(getActivity(), grupos, mapaItems);

        return adapter;
    }

    @Override
    public void botonAgregarProductoOnclick() {
        Intent intent = new Intent(getContext(), ListadoProductosAgregarPedidoActivity.class);
        intent.putExtra("cliente_seleccionado", obtenerClienteSeleccionado());
        intent.putExtra("fecha_seleccionada", obtenerFechaSeleccionada());

        startActivity(intent);
    }

    @Override
    public void btnConfirmarPedidoOnclick() {
        Intent intent = new Intent(getContext(), PedidosOpcionesActivity.class);
        presenter.actualizarInventario(fragmentCommunication.obtenerCantidadesProducto());
        startActivity(intent);
    }

    @Override
    public void btnSelectDateOnClick(View v) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void setDateText(Calendar calendario) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        txtFecha.setText(dateFormat.format(calendario.getTime()));
    }

    @Override
    public void configurarTextView(int itemsCount) {
        if (itemsCount == 0){
            txtProductoAgregado.setText("");
            imgProductoAgregado.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public String obtenerClienteSeleccionado() {
        return (String)listaClientes.getExpandableListAdapter().getGroup(0);
    }

    @Override
    public String obtenerFechaSeleccionada() {
        return txtFecha.getText().toString();
    }

    @Override
    public void setClienteSeleccionado(String clienteSeleccionado) {
        if (clienteSeleccionado != null){
            List<String> grupos = new ArrayList<>();
            grupos.add(clienteSeleccionado);
            presenter.setGrupos(grupos);
            Toast.makeText(getContext(), clienteSeleccionado, Toast.LENGTH_SHORT).show();

        }if (clienteSeleccionado == null){
            Toast.makeText(getContext(), "clienteSeleccionado == null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setFechaSeleccionada(String fechaSeleccionada) {
        if (fechaSeleccionada != null){
            txtFecha.setText(fechaSeleccionada);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtProductoAgregado = view.findViewById(R.id.txtProductoAgregado);
        imgProductoAgregado = view.findViewById(R.id.imgProductoAgregado);

        presenter = new ConfigurarPedidoFragmentoPresenter(getContext(), getActivity(), this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendario = new GregorianCalendar(i, i1, i2);
        setDateText(calendario);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
        presenter = new ConfigurarPedidoFragmentoPresenter(getContext(), getActivity(), this);
        fragmentCommunication.obtenerDatosSeleccionados();
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
}