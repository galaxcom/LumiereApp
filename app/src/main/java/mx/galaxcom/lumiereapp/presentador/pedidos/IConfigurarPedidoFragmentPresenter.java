package mx.galaxcom.lumiereapp.presentador.pedidos;

import java.util.HashMap;

/**
 * Created by david on 07/03/2018.
 */

public interface IConfigurarPedidoFragmentPresenter {
    public void obtenerClientesBaseDatos();

    public void mostrarClientesLV();

    public void obtenerMapaItemsBaseDatos();

    public void itemOnClick(int groupPosition, int childPosition);

    public void actualizarInventario(HashMap<String, Integer> productosAgregados);
}
