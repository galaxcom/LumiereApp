package mx.galaxcom.lumiereapp.vista.inventario;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import mx.galaxcom.lumiereapp.adapters.ProductoInventarioAdapter;

/**
 * Created by david on 23/02/2018.
 */

public interface ListaInventarioActivityView {
    public void inicializarAdapter(ProductoInventarioAdapter adapter);

    public ProductoInventarioAdapter crearAdapter(Context context, Cursor cursor);

    public void botonAgregarOnClick(View v);

    public ProductoInventarioAdapter getLVAdapter();

    public void cambiarCursorAdapterIntent();

    public String obtenerCategoriaProductosLV();
}
