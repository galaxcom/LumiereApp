package mx.galaxcom.lumiereapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.R;

/**
 * Created by david on 26/02/2018.
 */

public class ListaAgregarProductoAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> listaGrupos;
    HashMap<String, List<String>> stringListHashMap;

    public ListaAgregarProductoAdapter(Context context, List<String> listaGrupos, HashMap<String, List<String>> stringListHashMap) {
        this.context = context;
        this.listaGrupos = listaGrupos;
        this.stringListHashMap = stringListHashMap;
    }

    @Override
    public int getGroupCount() {
        return listaGrupos.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return stringListHashMap.get(listaGrupos.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listaGrupos.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return stringListHashMap.get(listaGrupos.get(i)).get(i1);
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
            view = inflater.inflate(R.layout.list_view_grupo, null);
        }

        TextView txtGrupoView = (TextView) view.findViewById(R.id.lvAgregarProductoGrupo);
        txtGrupoView.setText(tituloGrupo);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String productoItemNombre = (String) getChild(i, i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_item, null);
        }

        TextView txtItem = (TextView) view.findViewById(R.id.lvAgregarProductoItem);
        txtItem.setText(productoItemNombre);

        return view;
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

    public HashMap<String, List<String>> getStringListHashMap() {
        return stringListHashMap;
    }

    public void setStringListHashMap(HashMap<String, List<String>> stringListHashMap) {
        this.stringListHashMap = stringListHashMap;
    }
}
