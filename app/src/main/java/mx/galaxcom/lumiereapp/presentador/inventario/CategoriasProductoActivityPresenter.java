package mx.galaxcom.lumiereapp.presentador.inventario;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;
import mx.galaxcom.lumiereapp.vista.inventario.CategoriasProductoActivityView;

/**
 * Created by david on 19/02/2018.
 */

public class CategoriasProductoActivityPresenter implements ICategoriasProductoActivityPresenter {
    ConstructorBaseDatos constructorBaseDatos;
    CategoriasProductoActivityView vista;
    Context context;
    ArrayList<CategoriaInventario> categorias;
    Activity activity;

    public CategoriasProductoActivityPresenter (CategoriasProductoActivityView vista, Context context, Activity activity){
        this.vista = vista;
        this.context = context;
        this.activity = activity;

        obtenerCategoriasBaseDeDatos();
    }
    @Override
    public void obtenerCategoriasBaseDeDatos() {
        constructorBaseDatos = new ConstructorBaseDatos(context);
        categorias = constructorBaseDatos.obtenerCategorias();

        mostrarCategoriasRV();
    }

    @Override
    public void mostrarCategoriasRV() {
        vista.inizializarAdapter(vista.crearAdapter(categorias, activity));
    }
}
