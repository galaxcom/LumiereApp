package mx.galaxcom.lumiereapp.activities.inventario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.ListaAgregarProductoAdapter;
import mx.galaxcom.lumiereapp.pojo.ProductoParcelable;
import mx.galaxcom.lumiereapp.presentador.inventario.AgregarProductoActivityPresenter;
import mx.galaxcom.lumiereapp.vista.inventario.AgregarProductoActivityView;

public class AgregarProductoActivity extends AppCompatActivity implements AgregarProductoActivityView{

    private ExpandableListView listViewProductos;
    private AgregarProductoActivityPresenter presenter;
    private EditText etCantidadProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        listViewProductos = (ExpandableListView) findViewById(R.id.lvAgregarProducto);

        etCantidadProducto = (EditText) findViewById(R.id.etCantidadAgregarProducto);

        presenter = new AgregarProductoActivityPresenter(this, this, this);

        listViewProductos.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                presenter.itemOnClick(i, i1);

                return true;
            }
        });

    }

    @Override
    public ListaAgregarProductoAdapter crearAdapter(List<String> grupos, HashMap<String, List<String>> mapa, Context context) {
        ListaAgregarProductoAdapter adapter = new ListaAgregarProductoAdapter(context, grupos, mapa);

        return adapter;
    }

    @Override
    public void inicializarAdapter(ListaAgregarProductoAdapter adapter) {
        listViewProductos.setAdapter(adapter);
    }

    @Override
    public String obtenerCantidadET() {
        String sCantidad = etCantidadProducto.getText().toString();
        return sCantidad;
    }

    @Override
    public void btnAgregarOnClick(View v) {

        if (obtenerCantidadET().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor ingresa una cantidad.");
            builder.setCancelable(false);
            builder.setNegativeButton("Ok",
                    new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        if (obtenerProductoSeleccionado().equals("Seleccionar Producto")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor selecciona un producto.");
            builder.setCancelable(false);
            builder.setNegativeButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        if (!obtenerCantidadET().equals("") && !obtenerProductoSeleccionado().equals("Seleccionar Producto")){
            presenter.actualizarInventario(obtenerProductoSeleccionado(), Integer.parseInt(obtenerCantidadET()));
            Intent intent = new Intent(AgregarProductoActivity.this, ListaInventarioActivity.class);

            //String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS
            // + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA + " = " + "\"" + obtenerSubCategoriaProductosLV() + "\"";

            ProductoParcelable productoParcelable = new ProductoParcelable(null, obtenerProductoLV());

            intent.putExtra("producto", productoParcelable);

            startActivity(intent);
        }

    }

    @Override
    public String obtenerProductoLV(){
        ListaAgregarProductoAdapter adapter = (ListaAgregarProductoAdapter) listViewProductos.getExpandableListAdapter();

        String subcategoria = (String) adapter.getChild(0, 0);
        return subcategoria;
    }

    @Override
    public String obtenerProductoSeleccionado() {
        return listViewProductos.getExpandableListAdapter().getGroup(0).toString();
    }

}
