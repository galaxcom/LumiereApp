package mx.galaxcom.lumiereapp.activities.inventario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.ProductoInventarioAdapter;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.presentador.inventario.ListaInventarioActivityPresenter;
import mx.galaxcom.lumiereapp.vista.inventario.ListaInventarioActivityView;

public class ListaInventarioActivity extends AppCompatActivity implements ListaInventarioActivityView{

    ListView listViewProductos;
    ListaInventarioActivityPresenter presenter;
    ArrayList<ProductoInventario> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inventario);

        listViewProductos = (ListView) findViewById(R.id.listViewProductosInventario);

        productos = new ArrayList<>();

        presenter = new ListaInventarioActivityPresenter(this, this, this);
    }

    @Override
    public void inicializarAdapter(ProductoInventarioAdapter adapter) {
        listViewProductos.setAdapter(adapter);
    }

    @Override
    public ProductoInventarioAdapter crearAdapter(Context context, Cursor cursor) {
        ProductoInventarioAdapter adapter = new ProductoInventarioAdapter(context, cursor, this);

        return adapter;
    }

    @Override
    public void botonAgregarOnClick(View v) {
        Intent i = new Intent(ListaInventarioActivity.this, AgregarProductoActivity.class);
        i.putExtra("nombres_productos", obtenerNombresProductosLV());
        startActivity(i);
    }

    @Override
    public ProductoInventarioAdapter getLVAdapter() {
        return (ProductoInventarioAdapter) listViewProductos.getAdapter();
    }

    @Override
    public void cambiarCursorAdapterIntent() {
        ProductoInventarioAdapter adapter = (ProductoInventarioAdapter) listViewProductos.getAdapter();

        //adapter.cambiarCursorIntent();
    }

    @Override
    protected void onResume() {
        //presenter = new ListaInventarioActivityPresenter(this, this, this);
        super.onResume();

        ProductoInventarioAdapter adapter = (ProductoInventarioAdapter) listViewProductos.getAdapter();
        //if (adapter.isEmpty()){
            adapter.cambiarCursor(presenter.obtenerCursor());
        //}
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    public ArrayList<String> obtenerNombresProductosLV(){
        //long[] productos = new long[10];
        ArrayList<String> nombres = new ArrayList<>();

        ProductoInventarioAdapter adapter = (ProductoInventarioAdapter) listViewProductos.getAdapter();

        Cursor productos = adapter.getCursor();
        productos.moveToFirst();

        for (int i = 0; i < adapter.getCount(); i++){
            ProductoInventario productoInventario = new ProductoInventario();

            productoInventario.setNombre(productos.getString(3));

            nombres.add(productoInventario.getNombre());

            productos.moveToNext();
        }

        return nombres;
    }

    public void obtenerBundleCursor(){
        Bundle bundle = getIntent().getBundleExtra("cursor");

        bundle.get("cursor");
    }

    public String obtenerCategoriaProductosLV(){
        ProductoInventarioAdapter adapter = (ProductoInventarioAdapter) listViewProductos.getAdapter();

        Cursor productos = adapter.getCursor();
        productos.moveToFirst();

        ProductoInventario productoInventario = new ProductoInventario();

        productoInventario.setCategoria(productos.getString(1));

        return productoInventario.getCategoria();
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ListaInventarioActivity.this, SubCategoriasProductoActivity.class);
        intent.putExtra("categoriaLV", obtenerCategoriaProductosLV());
        setResult(RESULT_OK, intent);
        finish();
        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public boolean navigateUpTo(Intent upIntent) {
        Toast.makeText(this, "navigateUpTo", Toast.LENGTH_SHORT).show();
        upIntent.putExtra("categoriaLV", obtenerCategoriaProductosLV());
        return super.navigateUpTo(upIntent);
    }

}
