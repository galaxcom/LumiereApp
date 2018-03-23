package mx.galaxcom.lumiereapp.vista.inventario;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.adapters.CategoriaProductoAdapter;
import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;

/**
 * Created by david on 19/02/2018.
 */

public interface CategoriasProductoActivityView {
    public void crearLayoutManager();

    public CategoriaProductoAdapter crearAdapter(ArrayList<CategoriaInventario> categorias, Activity activity);

    public void inizializarAdapter(CategoriaProductoAdapter adapter);

}
