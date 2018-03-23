package mx.galaxcom.lumiereapp.presentador.inventario;

import android.database.Cursor;

import mx.galaxcom.lumiereapp.adapters.ProductoInventarioAdapter;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;

/**
 * Created by david on 21/02/2018.
 */

public interface IListaInventarioActivityPresenter {
    public void obtenerProductosBaseDeDatos();

    public void mostrarProductosLV();

}
