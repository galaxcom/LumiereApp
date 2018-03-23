package mx.galaxcom.lumiereapp.presentador.inventario;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import mx.galaxcom.lumiereapp.db.ConstructorBaseDatos;
import mx.galaxcom.lumiereapp.pojo.SubCategoriaInventario;
import mx.galaxcom.lumiereapp.vista.inventario.SubCategoriasProductoActivityView;

/**
 * Created by david on 20/02/2018.
 */

public class SubCategoriasProductoActivityPresenter implements ISubCategoriasProductoActivityPresenter {
    ConstructorBaseDatos constructor;
    SubCategoriasProductoActivityView vista;
    Context context;
    ArrayList<SubCategoriaInventario> subCategorias;
    Activity activity;

    public SubCategoriasProductoActivityPresenter(SubCategoriasProductoActivityView vista, Context context, Activity activity){
        this.vista = vista;
        this.context = context;
        this.activity = activity;

        obtenerSubCategoriasBaseDeDatos();
    }
    @Override
    public void obtenerSubCategoriasBaseDeDatos() {
        constructor = new ConstructorBaseDatos(context);
        subCategorias = constructor.obtenerSubCategorias();

        mostrarSubCategoriasRV();
    }

    @Override
    public void mostrarSubCategoriasRV() {
        vista.inicializarAdapter(vista.crearAdapter(subCategorias, activity));
    }
}
