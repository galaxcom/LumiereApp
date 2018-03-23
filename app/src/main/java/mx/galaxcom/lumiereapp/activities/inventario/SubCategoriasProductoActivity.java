package mx.galaxcom.lumiereapp.activities.inventario;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.SubCategoriaProductoAdapter;
import mx.galaxcom.lumiereapp.pojo.SubCategoriaInventario;
import mx.galaxcom.lumiereapp.presentador.inventario.SubCategoriasProductoActivityPresenter;
import mx.galaxcom.lumiereapp.vista.inventario.SubCategoriasProductoActivityView;

public class SubCategoriasProductoActivity extends AppCompatActivity implements SubCategoriasProductoActivityView{

    ArrayList<SubCategoriaInventario> subCategorias;
    RecyclerView listaSubCategorias;
    SubCategoriasProductoActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categorias_producto);

        listaSubCategorias = (RecyclerView) findViewById(R.id.rvSubCategoriasInventario);

        crearLayoutManager();

        subCategorias = new ArrayList<>();

        presenter = new SubCategoriasProductoActivityPresenter(this, this, this);
    }

    @Override
    public void crearLayoutManager() {
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        listaSubCategorias.setLayoutManager(glm);
    }

    @Override
    public SubCategoriaProductoAdapter crearAdapter(ArrayList<SubCategoriaInventario> subCategorias, Activity activity) {
        SubCategoriaProductoAdapter adapter = new SubCategoriaProductoAdapter(subCategorias, activity);

        return adapter;
    }

    @Override
    public void inicializarAdapter(SubCategoriaProductoAdapter adapter) {
        listaSubCategorias.setAdapter(adapter);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String categoria = data.getStringExtra("categoriaLV");
                SubCategoriaProductoAdapter adapter = (SubCategoriaProductoAdapter) listaSubCategorias.getAdapter();

                adapter.setExtraLV(categoria);
            }
        }
    }*/

    @Override
    public boolean navigateUpTo(Intent upIntent) {
        Toast.makeText(this, "navigateUpTo", Toast.LENGTH_SHORT).show();
        return super.navigateUpTo(upIntent);
    }
}
