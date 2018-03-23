package mx.galaxcom.lumiereapp.activities.pedidos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.ListaAgregarProductoAdapter;
import mx.galaxcom.lumiereapp.adapters.ListaProductoAgregarPedidoAdapter;
import mx.galaxcom.lumiereapp.adapters.ProductoAgregarPedidoERVAdapter;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;
import mx.galaxcom.lumiereapp.presentador.pedidos.ListadoProductosAgregarPedidoActivityPresenter;
import mx.galaxcom.lumiereapp.vista.pedidos.ListadoProductosAgregarPedidoActivityView;

public class ListadoProductosAgregarPedidoActivity extends AppCompatActivity implements ListadoProductosAgregarPedidoActivityView{

    private ExpandableListView listaProductos;
    private RecyclerView rvListaProductos;
    private ListadoProductosAgregarPedidoActivityPresenter presenter;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos_agregar_pedido);

        //listaProductos = (ExpandableListView) findViewById(R.id.rvAgregarProductosPedido);

        rvListaProductos = (RecyclerView) findViewById(R.id.rvProductosAgregarPedido);

        inicializarLayoutManager();

        presenter = new ListadoProductosAgregarPedidoActivityPresenter(this, this, this);

        setBtnContinuarClickable();

    }

    @Override
    public void inicializarAdapter(ListaProductoAgregarPedidoAdapter adapter) {
        listaProductos.setAdapter(adapter);
    }

    @Override
    public ListaProductoAgregarPedidoAdapter crearAdapter(Context context, List<String> listaGrupos, HashMap<String, List<String>> hashMapItems) {
        ListaProductoAgregarPedidoAdapter adapter = new ListaProductoAgregarPedidoAdapter(context, listaGrupos, hashMapItems);
        return adapter;
    }

    @Override
    public void inicializarAdapterRV(ProductoAgregarPedidoERVAdapter adapter) {
        rvListaProductos.setAdapter(adapter);
    }

    @Override
    public ProductoAgregarPedidoERVAdapter crearAdapterRV(List<CategoriasProductoERV> grupos) {
        return new ProductoAgregarPedidoERVAdapter(grupos);
    }

    @Override
    public void inicializarLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaProductos.setLayoutManager(llm);
    }

    @Override
    public ArrayList<String> obtenerProductosSeleccionados() {
        ArrayList<String> productosSeleccionados = new ArrayList<>();

        for(int i = 0; i < rvListaProductos.getChildCount(); i++){
            View item = rvListaProductos.getChildAt(i);
            CheckedTextView checked = (CheckedTextView) item.findViewById(R.id.item_producto_agregar_pedido);
            if (checked != null){
                if(checked.isChecked()){
                    String nombre = checked.getText().toString();

                    productosSeleccionados.add(nombre);
                }
            }
        }

        return productosSeleccionados;
    }

    @Override
    public ArrayList<String> obtenerCantidadesProductosSeleccionados() {
        ArrayList<String> cantidades = new ArrayList<>();

        ProductoAgregarPedidoERVAdapter adapter = (ProductoAgregarPedidoERVAdapter) rvListaProductos.getAdapter();

        for(int i = 0; i < rvListaProductos.getChildCount(); i++){
            View item = rvListaProductos.getChildAt(i);
            CheckedTextView checked = (CheckedTextView) item.findViewById(R.id.item_producto_agregar_pedido);
            if (checked != null){
                if(checked.isChecked()){
                    EditText etCantidad = item.findViewById(R.id.etCantidadProductoPedido);
                    String cantidad = etCantidad.getText().toString();

                    cantidades.add(cantidad);
                }
            }
        }

        return cantidades;
    }

    @Override
    public ArrayList<Integer> obtenerPrecioUnitarioProductosSeleccionados() {
        ArrayList<Integer> precios = new ArrayList<>();

        for(int i = 0; i < rvListaProductos.getChildCount(); i++){
            View item = rvListaProductos.getChildAt(i);
            CheckedTextView checked = (CheckedTextView) item.findViewById(R.id.item_producto_agregar_pedido);
            if (checked != null){
                if(checked.isChecked()){
                    EditText etCantidad = item.findViewById(R.id.etPrecioUnitarioAgregarProducto);

                    Integer cantidad = Integer.parseInt(etCantidad.getText().toString());

                    precios.add(cantidad);
                }
            }
        }

        return precios;
    }

    @Override
    public void setBtnContinuarClickable() {
        for(int i = 0; i < rvListaProductos.getChildCount(); i++){
            View item = rvListaProductos.getChildAt(i);
            CheckedTextView checked = (CheckedTextView) item.findViewById(R.id.item_producto_agregar_pedido);
            if (checked != null){
                if(checked.isChecked()){
                    EditText etPrecioUnitario = item.findViewById(R.id.etPrecioUnitarioAgregarProducto);
                    EditText etCantidad = item.findViewById(R.id.etCantidadProductoPedido);

                    if (etPrecioUnitario.getText().toString().equals("") || etCantidad.getText().toString().equals("")){
                        btnContinuar = findViewById(R.id.btnProducto);
                        btnContinuar.setClickable(false);
                    }
                }
            }
        }

    }


    @Override
    public void btnContinuarOnClick(View v) {
        for(int i = 0; i < rvListaProductos.getChildCount(); i++){
            View item = rvListaProductos.getChildAt(i);
            CheckedTextView checked = (CheckedTextView) item.findViewById(R.id.item_producto_agregar_pedido);
            if (checked != null){
                if(checked.isChecked()){
                    EditText etPrecioUnitario = item.findViewById(R.id.etPrecioUnitarioAgregarProducto);
                    EditText etCantidad = item.findViewById(R.id.etCantidadProductoPedido);

                    if (etPrecioUnitario.getText().toString().equals("") || etCantidad.getText().toString().equals("")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Por favor ingresa un valor en los campos de los productos que seleccionaste.");
                        builder.setCancelable(false);
                        builder.setNegativeButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else{
                        Intent intent = new Intent(ListadoProductosAgregarPedidoActivity.this, AgregarPedidoActivity.class);
                        intent.putExtra("nombres_productos", obtenerProductosSeleccionados());
                        intent.putExtra("cantidades_productos", obtenerCantidadesProductosSeleccionados());
                        intent.putExtra("precios_productos", obtenerPrecioUnitarioProductosSeleccionados());
                        intent.putExtra("texto", "Has agregado productos a este pedido");
                        intent.putExtra("cliente_seleccionado", getIntent().getStringExtra("cliente_seleccionado"));
                        intent.putExtra("fecha_seleccionada", getIntent().getStringExtra("fecha_seleccionada"));
                        startActivity(intent);
                    }
                }
            }
        }

        //intent
    }

}