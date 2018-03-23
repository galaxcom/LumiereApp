package mx.galaxcom.lumiereapp.presentador.inventario;

/**
 * Created by david on 26/02/2018.
 */

public interface IAgregarProductoActivityPresenter {
    public void obtenerProductosBaseDatos();

    public void mostrarItemsLV();

    public void obtenerMapaItemsBaseDatos();

    public void actualizarInventario(String productoSeleccionado, int cantidadAgregar);

    public void itemOnClick(int groupPosition, int childPosition);
}
