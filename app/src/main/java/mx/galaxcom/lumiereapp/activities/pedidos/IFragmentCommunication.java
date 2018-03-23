package mx.galaxcom.lumiereapp.activities.pedidos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by david on 20/03/2018.
 */

public interface IFragmentCommunication {

    int getProductosCount();

    void isLVEmpty();

    void obtenerDatosSeleccionados();

    HashMap<String, Integer> obtenerCantidadesProducto();
}
