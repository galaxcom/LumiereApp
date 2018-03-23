package mx.galaxcom.lumiereapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;

/**
 * Created by david on 07/03/2018.
 */

public class ListaClientesAdapter extends BaseExpandableListAdapter {
    public final LayoutInflater inf;
    Context context;
    List<String> listaGrupos;
    HashMap<String, List<String>> hashMapItems;

    public ListaClientesAdapter(Context context, List<String> listaGrupos, HashMap<String, List<String>> hashMapItems){
        this.context = context;
        this.listaGrupos = listaGrupos;
        this.hashMapItems = hashMapItems;
        inf = LayoutInflater.from(context);
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
        /*ViewHolder holder;

        if (view == null) {
            view = inf.inflate(R.layout.list_view_grupo, viewGroup, false);

            holder = new ViewHolder();
            holder.text = (TextView) view.findViewById(R.id.lvClientesGrupo);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.text.setText(getGroup(i).toString());

        return view;*/
        String tituloGrupo = (String)getGroup(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_clientes_grupo, null);
        }

        TextView txtGrupoView = (TextView) view.findViewById(R.id.lvClientesGrupo);
        txtGrupoView.setText(tituloGrupo);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inf.inflate(R.layout.list_view_clientes_item, viewGroup, false);
            holder = new ViewHolder();

            holder.text = (TextView) view.findViewById(R.id.lvClientesItem);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.text.setText(getChild(i, i1).toString());

        return view;
        /*final String clienteItemNombre = (String) getChild(i, i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_clientes_item, null);
        }

        TextView txtItem = view.findViewById(R.id.lvAgregarProductoItem);

        txtItem.setText(clienteItemNombre);

        return view;*/
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public List<String> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<String> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public HashMap<String, List<String>> getHashMapItems() {
        return hashMapItems;
    }

    public void setHashMapItems(HashMap<String, List<String>> hashMapItems) {
        this.hashMapItems = hashMapItems;
    }

    private class ViewHolder {
        TextView text;
    }
}
