package mx.galaxcom.lumiereapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.db.BaseDatos;
import mx.galaxcom.lumiereapp.db.ConstantesBaseDatos;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;

/**
 * Created by david on 20/02/2018.
 */

public class ProductoInventarioAdapter extends CursorAdapter {

    Context context;
    Activity activity;

    public ProductoInventarioAdapter(Context context, Cursor cursor, Activity activity){
        super(context, cursor, 0);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_lista_inventario, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
            TextView txtNombreProducto = view.findViewById(R.id.txtProductoListaInventario);
            TextView txtCantidadProducto = view.findViewById(R.id.txtCantidadProductoListaInventario);

            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE));
            int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD));

            txtNombreProducto.setText(nombre);
            txtCantidadProducto.setText(String.valueOf(cantidad));

            if (cantidad < 50){
                txtCantidadProducto.setTextColor(context.getResources().getColor(R.color.colorCantidadProductoCRITICO));
            }

            if (cantidad < 100 && cantidad >= 50){
                txtCantidadProducto.setTextColor(context.getResources().getColor(R.color.colorCantidadProductoADVERTENCIA));
            }

            if (cantidad > 100 || cantidad == 100){
                txtCantidadProducto.setTextColor(context.getResources().getColor(R.color.colorCantidadProductoNORMAL));
            }
    }

    public void cambiarCursor(Cursor cursor){
        if (super.isEmpty()){

            super.changeCursor(cursor);

        }
    }
}
