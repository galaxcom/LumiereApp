package mx.galaxcom.lumiereapp.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.activities.pedidos.IFragmentCommunication;
import mx.galaxcom.lumiereapp.db.BaseDatos;
import mx.galaxcom.lumiereapp.db.ConstantesBaseDatos;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.vista.pedidos.AgregarPedidoActivityView;
import mx.galaxcom.lumiereapp.vista.pedidos.ListaProductosPedidoFragmentView;

/**
 * Created by david on 14/03/2018.
 */

public class ListaProductosAgregadosAdapter extends BaseAdapter {
    Activity activity;
    Context context;
    ArrayList<ProductoInventario> productosAgregados = new ArrayList<>();
    ListaProductosPedidoFragmentView vista;

    public ListaProductosAgregadosAdapter(Activity activity, Context context, ListaProductosPedidoFragmentView vista, ArrayList<ProductoInventario> productosAgregados){
        this.activity = activity;
        this.context = context;
        this.vista = vista;
        this.productosAgregados = productosAgregados;
    }

    @Override
    public int getCount() {
        return productosAgregados.size();
    }

    @Override
    public Object getItem(int i) {
        return productosAgregados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.item_list_view_productos_agregados, viewGroup, false);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ProductoInventario producto = (ProductoInventario) getItem(i);

        //sets the text for item name and item description from the current item object
        viewHolder.categoria.setText(producto.getCategoria());
        viewHolder.subcategoria.setText(producto.getSubCategoria());
        viewHolder.nombreProducto.setText(producto.getNombre());
        viewHolder.cantidad.setText("Cantidad: " + String.valueOf(producto.getCantidad()));
        viewHolder.precioUnitario.setText("Precio Unitario: " + String.valueOf(producto.getPrecioVenta()));

        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("¿Quieres eliminar este producto del pedido?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                productosAgregados.remove(i);
                                notifyDataSetChanged();
                                ((IFragmentCommunication)activity).isLVEmpty();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        // returns the view for the current row
        return view;
    }

    private class ViewHolder{
        TextView categoria;
        TextView subcategoria;
        TextView nombreProducto;
        TextView cantidad;
        TextView precioUnitario;
        ImageView btnRemove;

        public ViewHolder(View view){
            categoria = (TextView) view.findViewById(R.id.txtCategoriaItemProductos);
            subcategoria = (TextView) view.findViewById(R.id.txtSubcategoriaItemProductos);
            nombreProducto = (TextView) view.findViewById(R.id.txtProductoItemProductos);
            cantidad = (TextView) view.findViewById(R.id.txtCantidadItemProductos);
            precioUnitario = (TextView) view.findViewById(R.id.txtPrecioUnitarioItemProductos);
            btnRemove = (ImageView) view.findViewById(R.id.imgRemoveItem);
        }
    }
}
