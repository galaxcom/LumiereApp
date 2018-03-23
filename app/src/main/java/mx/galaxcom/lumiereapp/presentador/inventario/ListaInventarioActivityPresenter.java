package mx.galaxcom.lumiereapp.presentador.inventario;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.db.BaseDatos;
import mx.galaxcom.lumiereapp.db.ConstantesBaseDatos;
import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.pojo.ProductoParcelable;
import mx.galaxcom.lumiereapp.vista.inventario.ListaInventarioActivityView;

/**
 * Created by david on 23/02/2018.
 */

public class ListaInventarioActivityPresenter implements IListaInventarioActivityPresenter{
    ConstructorBaseDatos constructor;
    ListaInventarioActivityView vista;
    Context context;
    ArrayList<ProductoInventario> productos;
    Activity activity;

    public ListaInventarioActivityPresenter (ListaInventarioActivityView vista, Context context, Activity activity){
        this.vista = vista;
        this.context = context;
        this.activity = activity;

        obtenerProductosBaseDeDatos();
    }

    @Override
    public void obtenerProductosBaseDeDatos() {
        constructor = new ConstructorBaseDatos(context);
        productos = constructor.obtenerProductosInventario();

        mostrarProductosLV();
    }

    @Override
    public void mostrarProductosLV() {
        BaseDatos baseDatos = new BaseDatos(context);
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        vista.inicializarAdapter(vista.crearAdapter(context, baseDatos.obtenerProductosNecesarios(activity)));
    }

    public Cursor obtenerCursor(){
        ProductoParcelable producto = activity.getIntent().getParcelableExtra("producto");

        BaseDatos baseDatos = new BaseDatos(context);
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " LIMIT 7", null);

        if (producto != null){
            cursor = constructor.obtenerCursorProductosLV(producto.getNombre());
        }else{
            cursor = baseDatos.obtenerProductosNecesarios(activity);

            Toast.makeText(context, "producto = null", Toast.LENGTH_SHORT).show();
        }

        return cursor;
    }

    public String obtenerCategoria(String categoriaProducto){
        BaseDatos baseDatos = new BaseDatos(context);

        return baseDatos.obtenerCategoriaNecesaria(categoriaProducto);
    }
}
