package mx.galaxcom.lumiereapp.adapters.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;

/**
 * Created by david on 15/03/2018.
 */

public class CategoriaViewHolder extends GroupViewHolder {
    private TextView nombreCategoria;

    public CategoriaViewHolder(View itemView) {
        super(itemView);
        nombreCategoria = itemView.findViewById(R.id.grupoProductoAgregarPedido);
    }


    public void setNombreCategoria(ExpandableGroup categoria){
        nombreCategoria.setText(((CategoriasProductoERV) categoria).getNombreCategoria());
    }
}
