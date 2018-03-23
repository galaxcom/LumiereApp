package mx.galaxcom.lumiereapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.activities.inventario.SubCategoriasProductoActivity;
import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;

/**
 * Created by david on 19/02/2018.
 */

public class CategoriaProductoAdapter extends RecyclerView.Adapter<CategoriaProductoAdapter.CategoriaInventarioViewHolder> {

    ArrayList<CategoriaInventario> categorias;
    Activity activity;

    public CategoriaProductoAdapter(ArrayList<CategoriaInventario> categorias, Activity activity){
        this.categorias = categorias;
        this.activity = activity;
    }

    @Override
    public CategoriaInventarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_productos, parent, false);
        return new CategoriaInventarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriaInventarioViewHolder holder, int position) {
        final CategoriaInventario categoria = categorias.get(position);
        holder.foto.setImageResource(categoria.getFotoProducto());
        holder.categoria.setText(categoria.getNombreCategoria());

        holder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, SubCategoriasProductoActivity.class);
                i.putExtra("categoria", categoria.getNombreCategoria());
                activity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public static class CategoriaInventarioViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView categoria;

        public CategoriaInventarioViewHolder(View itemView) {
            super(itemView);
            foto = (ImageView) itemView.findViewById(R.id.imgCategoriaInventario);
            categoria = (TextView) itemView.findViewById(R.id.txtCategoriaInventario);


        }
    }
}
