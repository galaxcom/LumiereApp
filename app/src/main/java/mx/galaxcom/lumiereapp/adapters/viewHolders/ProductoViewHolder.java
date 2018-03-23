package mx.galaxcom.lumiereapp.adapters.viewHolders;

import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;

import mx.galaxcom.lumiereapp.R;

/**
 * Created by david on 15/03/2018.
 */

public class ProductoViewHolder extends CheckableChildViewHolder {

    private CheckedTextView nombreProducto;
    private EditText etCantidad;
    private EditText etPrecioUnitario;

    public ProductoViewHolder(View itemView) {
        super(itemView);
        nombreProducto = itemView.findViewById(R.id.item_producto_agregar_pedido);
        etCantidad = itemView.findViewById(R.id.etCantidadProductoPedido);
        etPrecioUnitario = itemView.findViewById(R.id.etPrecioUnitarioAgregarProducto);
    }

    @Override
    public Checkable getCheckable() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto){
        this.nombreProducto.setText(nombreProducto);
    }

    public EditText getEtCantidad(){
        return etCantidad;
    }

    public EditText getEtPrecioUnitario(){
        return etPrecioUnitario;
    }
}
