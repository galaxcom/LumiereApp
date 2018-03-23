package mx.galaxcom.lumiereapp.vista.inventario;

import android.app.Activity;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.adapters.SubCategoriaProductoAdapter;
import mx.galaxcom.lumiereapp.pojo.SubCategoriaInventario;

/**
 * Created by david on 20/02/2018.
 */

public interface SubCategoriasProductoActivityView {
     public void crearLayoutManager();

     public SubCategoriaProductoAdapter crearAdapter(ArrayList<SubCategoriaInventario> subCategorias, Activity activity);

     public void inicializarAdapter(SubCategoriaProductoAdapter adapter);

}
