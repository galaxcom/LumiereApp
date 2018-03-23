package mx.galaxcom.lumiereapp.activities.inventario;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.R;
import mx.galaxcom.lumiereapp.adapters.CategoriaProductoAdapter;
import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;
import mx.galaxcom.lumiereapp.presentador.inventario.CategoriasProductoActivityPresenter;
import mx.galaxcom.lumiereapp.presentador.inventario.ICategoriasProductoActivityPresenter;
import mx.galaxcom.lumiereapp.vista.inventario.CategoriasProductoActivityView;

public class CategoriasProductoActivity extends AppCompatActivity implements CategoriasProductoActivityView{
    ArrayList<CategoriaInventario> categorias;
    RecyclerView listaCategorias;
    ICategoriasProductoActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_producto);

        listaCategorias = (RecyclerView) findViewById(R.id.rvCategoriasInventario);

        crearLayoutManager();
        categorias = new ArrayList<CategoriaInventario>();

        presenter = new CategoriasProductoActivityPresenter(this, this, this);

    }

    @Override
    public void crearLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaCategorias.setLayoutManager(llm);
    }

    @Override
    public CategoriaProductoAdapter crearAdapter(ArrayList<CategoriaInventario> categorias, Activity activity) {
        CategoriaProductoAdapter adapter = new CategoriaProductoAdapter(categorias, activity);
        return adapter;
    }

    @Override
    public void inizializarAdapter(CategoriaProductoAdapter adapter) {
        listaCategorias.setAdapter(adapter);
    }

}
