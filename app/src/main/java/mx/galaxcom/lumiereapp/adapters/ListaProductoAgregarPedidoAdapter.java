package mx.galaxcom.lumiereapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;

/**
 * Created by david on 13/03/2018.
 */

public class ListaProductoAgregarPedidoAdapter extends BaseExpandableListAdapter{
    Context context;
    List<String> listaGrupos;
    HashMap<String, List<String>> hashMapItems;

    public ListaProductoAgregarPedidoAdapter(Context context, List<String> listaGrupos, HashMap<String, List<String>> hashMapItems){
        this.context = context;
        this.listaGrupos = listaGrupos;
        this.hashMapItems = hashMapItems;

    }

    @Override
    public int getGroupCount() {
        return listaGrupos.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return hashMapItems.get(listaGrupos.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listaGrupos.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return hashMapItems.get(listaGrupos.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String tituloGrupo = (String)getGroup(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grupo_producto_agregar_pedido_elv, null);
        }

        TextView txtGrupoView = (TextView) view.findViewById(R.id.grupoProductoAgregarPedidoELV);
        txtGrupoView.setText(tituloGrupo);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String item = (String) getChild(i, i1);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_producto_agregar_pedido, null);
        }

        TextView txtGrupoView = (TextView) view.findViewById(R.id.item_producto_agregar_pedido);
        txtGrupoView.setText(item);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
