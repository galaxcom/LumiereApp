package mx.galaxcom.lumiereapp.db;

/**
 * Created by david on 19/02/2018.
 */

public final class ConstantesBaseDatos {
    public static final String DATABSE_NAME = "1Lumiere";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLA_CATEGORIAS = "categorias";
    public static final String TABLA_CATEGORIAS_ID = "_id";
    public static final String TABLA_CATEGORIAS_FOTO = "foto";
    public static final String TABLA_CATEGORIAS_NOMBRE = "nombre";

    public static final String TABLA_SUB_CATEGORIAS = "subcategorias";
    public static final String TABLA_SUB_CATEGORIAS_ID = "_id";
    public static final String TABLA_SUB_CATEGORIAS_CATEGORIA = "categoria";
    public static final String TABLA_SUB_CATEGORIAS_FOTO = "foto";
    public static final String TABLA_SUB_CATEGORIAS_NOMBRE = "nombre";

    public static final String TABLA_PRODUCTOS = "productos";
    public static final String TABLA_PRODUCTOS_ID = "_id";
    public static final String TABLA_PRODUCTOS_CATEGORIA = "categoria";
    public static final String TABLA_PRODUCTOS_SUBCATEGORIA = "subcategoria";
    public static final String TABLA_PRODUCTOS_NOMBRE = "nombre";
    public static final String TABLA_PRODUCTOS_CANTIDAD = "cantidad";
    public static final String TABLA_PRODUCTOS_PRECIO_VENTA = "precio_venta";

    public static final String TABLA_CLIENTES = "clientes";
    public static final String TABLA_CLIENTES_ID = "id";
    public static final String TABLA_CLIENTES_NOMBRE = "nombre";
    public static final String TABLA_CLIENTES_CORREO = "correo";
}
