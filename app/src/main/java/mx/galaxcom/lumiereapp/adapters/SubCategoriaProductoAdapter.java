package mx.galaxcom.lumiereapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.activities.inventario.ListaInventarioActivity;
import mx.galaxcom.lumiereapp.db.BaseDatos;
import mx.galaxcom.lumiereapp.db.ConstantesBaseDatos;
import mx.galaxcom.lumiereapp.pojo.SubCategoriaInventario;

/**
 * Created by david on 20/02/2018.
 */

public class SubCategoriaProductoAdapter extends RecyclerView.Adapter<SubCategoriaProductoAdapter.SubCategoriaInventarioViewHolder> {
    ArrayList<SubCategoriaInventario> subCategorias;
    Activity activity;
    String categoriaSeleccionada;
    String extraLV = "O.o";

    public SubCategoriaProductoAdapter(ArrayList<SubCategoriaInventario> subCategorias, Activity activity){
        this.subCategorias = subCategorias;
        this.activity = activity;

    }

    @Override
    public SubCategoriaProductoAdapter.SubCategoriaInventarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_sub_categorias, parent, false);
        return new SubCategoriaProductoAdapter.SubCategoriaInventarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubCategoriaProductoAdapter.SubCategoriaInventarioViewHolder holder, int position) {
        final SubCategoriaInventario subCategoria = subCategorias.get(position);
        holder.foto.setImageResource(subCategoria.getFotoProducto());


        if (activity.getIntent().hasExtra("categoria")){
            categoriaSeleccionada = activity.getIntent().getStringExtra("categoria");
        }else if (activity.getIntent().hasExtra("categoriaLV")){
            categoriaSeleccionada = activity.getIntent().getStringExtra("categoriaLV");
            //Toast.makeText(activity, "NO EXISTE EL EXTRA", Toast.LENGTH_SHORT).show();
            //extraLV = activity.getIntent().getStringExtra("categoriaLV");
        }

        BaseDatos baseDatos = new BaseDatos(activity);
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS +
                " WHERE " + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_CATEGORIA + " = " + "\"" + categoriaSeleccionada + "\"";

        Cursor subCategoriasNecesarias = db.rawQuery(query, null);

        subCategoriasNecesarias.moveToFirst();

        int posicionActual = holder.getAdapterPosition();

        subCategoria.setNombreSubCategoria(subCategoriasNecesarias.getString(3));
        holder.categoria.setText(subCategoria.getNombreSubCategoria());
        //holder.categoria.setText(extraLV);
        for (int i = 0; i < subCategorias.size(); i++){
            if (position > i++){
                subCategoriasNecesarias.moveToNext();
                subCategoria.setNombreSubCategoria(subCategoriasNecesarias.getString(3));
                holder.categoria.setText(subCategoria.getNombreSubCategoria());

            }
        }
        //holder.categoria.setText(String.valueOf(position));

        subCategoriasNecesarias.moveToNext();


        holder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, ListaInventarioActivity.class);
                i.putExtra("subcategoria", subCategoria.getNombreSubCategoria());

                activity.startActivity(i);
            }
        });

        db.close();

    }

    @Override
    public int getItemCount() {
        return subCategorias.size();
    }

    public String getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(String categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public String getExtraLV() {
        return extraLV;
    }

    public void setExtraLV(String extraLV) {
        this.extraLV = extraLV;
    }

    public static class SubCategoriaInventarioViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView categoria;

        public SubCategoriaInventarioViewHolder(View itemView) {
            super(itemView);
            foto = (ImageView) itemView.findViewById(R.id.imgSubCategoriaInventario);
            categoria = (TextView) itemView.findViewById(R.id.txtSubCategoriaInventario);
        }
    }

}
