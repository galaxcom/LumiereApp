package mx.galaxcom.lumiereapp.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.galaxcom.lumiereapp.adapters.ProductoAgregarPedidoERVAdapter;
import mx.galaxcom.lumiereapp.pojo.CategoriaInventario;
import mx.galaxcom.lumiereapp.pojo.CategoriasProductoERV;
import mx.galaxcom.lumiereapp.pojo.Cliente;
import mx.galaxcom.lumiereapp.pojo.ProductoInventario;
import mx.galaxcom.lumiereapp.pojo.SubCategoriaInventario;

/**
 * Created by david on 19/02/2018.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context){
        super(context, ConstantesBaseDatos.DATABSE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaCategorias = "CREATE TABLE " + ConstantesBaseDatos.TABLA_CATEGORIAS + "("
                + ConstantesBaseDatos.TABLA_CATEGORIAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLA_CATEGORIAS_FOTO + " INTEGER, "
                + ConstantesBaseDatos.TABLA_CATEGORIAS_NOMBRE + " TEXT UNIQUE" + ")";

        String queryCrearTablaSubCategorias = "CREATE TABLE " + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS + "("
                + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_CATEGORIA + " TEXT, "
                + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_FOTO + " INTEGER, "
                + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_NOMBRE + " TEXT UNIQUE, "
                + "FOREIGN KEY (" + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_CATEGORIA + ") "
                + "REFERENCES " + ConstantesBaseDatos.TABLA_CATEGORIAS + "(" + ConstantesBaseDatos.TABLA_CATEGORIAS_NOMBRE + ")" +
                ")";

        String queryCrearTablaProductos = "CREATE TABLE " + ConstantesBaseDatos.TABLA_PRODUCTOS + "("
                + ConstantesBaseDatos.TABLA_PRODUCTOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA + " TEXT, "
                + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + " TEXT, "
                + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " TEXT UNIQUE, "
                + ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD + " INTEGER, "
                + ConstantesBaseDatos.TABLA_PRODUCTOS_PRECIO_VENTA + " REAL, "
                + "FOREIGN KEY (" + ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA + ") "
                + "REFERENCES " + ConstantesBaseDatos.TABLA_CATEGORIAS + "(" + ConstantesBaseDatos.TABLA_CATEGORIAS_NOMBRE + ")"
                + "FOREIGN KEY (" + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + ") "
                + "REFERENCES " + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS + "(" + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS_NOMBRE + ")" +
                ")";

        String queryCrearTablaClientes = "CREATE TABLE " + ConstantesBaseDatos.TABLA_CLIENTES + "("
                + ConstantesBaseDatos.TABLA_CLIENTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLA_CLIENTES_NOMBRE + " TEXT UNIQUE, "
                + ConstantesBaseDatos.TABLA_CLIENTES_CORREO + " TEXT" + ")";

        db.execSQL(queryCrearTablaCategorias);
        db.execSQL(queryCrearTablaSubCategorias);
        db.execSQL(queryCrearTablaProductos);
        db.execSQL(queryCrearTablaClientes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLA_CATEGORIAS);
        onCreate(db);
    }

    //CATEGORÍAS
    public ArrayList<CategoriaInventario> obtenerCategorias(){
        ArrayList<CategoriaInventario> categorias = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_CATEGORIAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosCategorias = db.rawQuery(query, null);

        while(registrosCategorias.moveToNext()){
            CategoriaInventario categoria = new CategoriaInventario();

            categoria.setId(registrosCategorias.getInt(0));
            categoria.setFotoProducto(registrosCategorias.getInt(1));
            categoria.setNombreCategoria(registrosCategorias.getString(2));

            categorias.add(categoria);
        }

        db.close();

        return categorias;
    }

    public void insertarCategorias(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_CATEGORIAS, null, contentValues);
        db.close();
    }

    public String obtenerCategoriaNecesaria(String categoriaProducto){
        String categoria;

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_CATEGORIAS + " WHERE " + ConstantesBaseDatos.TABLA_CATEGORIAS_NOMBRE + " = " + categoriaProducto;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor categoriaC = db.rawQuery(query, null);

        categoriaC.moveToFirst();
        categoria = categoriaC.getString(2);

        return categoria;
    }


    //SUB-CATEGORÍAS
    public ArrayList<SubCategoriaInventario> obtenerSubCategorias(){
        ArrayList<SubCategoriaInventario> subCategorias = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_SUB_CATEGORIAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosSubCategorias = db.rawQuery(query, null);

        while (registrosSubCategorias.moveToNext()){
            SubCategoriaInventario subCategoria = new SubCategoriaInventario();

            subCategoria.setId(registrosSubCategorias.getInt(0));
            subCategoria.setNombreCategoria(registrosSubCategorias.getString(1));
            subCategoria.setFotoProducto(registrosSubCategorias.getInt(2));
            subCategoria.setNombreSubCategoria(registrosSubCategorias.getString(3));

            subCategorias.add(subCategoria);
        }

        db.close();

        return subCategorias;
    }

    public void insertarSubCategorias(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_SUB_CATEGORIAS, null, contentValues);
        db.close();
    }

    //PRODUCTOS
    public ArrayList<ProductoInventario> obtenerProductosInventario(){
        ArrayList<ProductoInventario> productos = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosProductos = db.rawQuery(query, null);

        while(registrosProductos.moveToNext()){
            ProductoInventario producto = new ProductoInventario();

            producto.setId(registrosProductos.getInt(0));
            producto.setCategoria(registrosProductos.getString(1));
            producto.setSubCategoria(registrosProductos.getString(2));
            producto.setNombre(registrosProductos.getString(3));
            producto.setCantidad(registrosProductos.getInt(4));
            producto.setPrecioVenta(registrosProductos.getFloat(5));

            productos.add(producto);
        }

        db.close();

        return productos;
    }

    public Cursor obtenerProductosNecesarios(Activity activity){
        String subCategoriaElegida = activity.getIntent().getStringExtra("subcategoria");

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + " = " + "\"" + subCategoriaElegida + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosProductos = db.rawQuery(query, null);

        return registrosProductos;
    }

    public HashMap<String, List<String>> obtenerProductosInventarioMapa(List<String> grupos, String subCategoriaElegida){
        HashMap<String, List<String>> mapaItems = new HashMap<>();
        List<String> nombreItems = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + " = " + "\"" + subCategoriaElegida+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosProductos = db.rawQuery(query, null);

        while(registrosProductos.moveToNext()){
            ProductoInventario producto = new ProductoInventario();

            producto.setId(registrosProductos.getInt(0));
            producto.setCategoria(registrosProductos.getString(1));
            producto.setSubCategoria(registrosProductos.getString(2));
            producto.setNombre(registrosProductos.getString(3));
            producto.setCantidad(registrosProductos.getInt(4));
            producto.setPrecioVenta(registrosProductos.getFloat(5));

            nombreItems.add(producto.getNombre());
        }

        db.close();

        mapaItems.put(grupos.get(0), nombreItems);

        return mapaItems;
    }

    public void insertarProductosInventario(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_PRODUCTOS, null, contentValues);
        db.close();
    }

    public void actualizarCantidadProducto(String productoSeleccionado, int cantidadParaAgregar){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD + " FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS
                    + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " = " + "\"" + productoSeleccionado + "\"";

            Cursor cantidadActualProductoC = db.rawQuery(query, null);

            cantidadActualProductoC.moveToFirst();

            int cantidadActualProducto = cantidadActualProductoC.getInt(0);
            cantidadActualProducto += cantidadParaAgregar;

            String queryUpdate = "UPDATE " + ConstantesBaseDatos.TABLA_PRODUCTOS
                    + " SET " + ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD + " = " + cantidadActualProducto
                    + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " = " + "\"" + productoSeleccionado + "\"";

            db.execSQL(queryUpdate);
            db.close();


    }

    public HashMap<String, List<String>> obtenerProductosInventarioNecesariosMapa(List<String> grupos, Activity activity){
        HashMap<String, List<String>> mapaItems = new HashMap<>();
        ArrayList<String> nombreItems = obtenerArrayListProductosNecesarios(activity);

        /*for (int i = 0; i < productos.size(); i++){
            String nombre;

            nombre = productos.get(i).getNombre();

            nombreItems.add(nombre);
        }*/
        mapaItems.put(grupos.get(0), nombreItems);

        return mapaItems;
    }

    public ArrayList<String> obtenerArrayListProductosNecesarios(Activity activity){
        ArrayList<String> productosNecesarios = activity.getIntent().getStringArrayListExtra("nombres_productos");
        //SQLiteDatabase db = this.getWritableDatabase();
        /*for (int i = 0; i < productosNecesarios.length; i++){
            String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_ID + " = " + productosNecesarios[i];

            Cursor productos = db.rawQuery(query, null);
            productos.moveToFirst();

            String nombreProducto = productos.getString(3);

            nombres.add(nombreProducto);
        }
        /*SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + " = " + "\"" + "Sobre" + "\"";
         Cursor productos = db.rawQuery(query, null);
         ArrayList<ProductoInventario> productosNecesarios = new ArrayList<>();

         while (productos.moveToNext()){
             ProductoInventario producto = new ProductoInventario();

             producto.setNombre(productos.getString(3));

             productosNecesarios.add(producto);
         }*/

         return productosNecesarios;
    }

    public Cursor obtenerCursorProductosLV(String producto){
        SQLiteDatabase db = this.getWritableDatabase();
        String querySubcategoria = "SELECT " + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + " FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS
                + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " = " + "\"" + producto + "\"";

        Cursor subcategorias = db.rawQuery(querySubcategoria, null);

        subcategorias.moveToFirst();
        String subcategoria = subcategorias.getString(0);

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_SUBCATEGORIA + " = " + "\"" + subcategoria + "\"";
        Cursor productos = db.rawQuery(query, null);

        return productos;
    }

    public HashMap<String, List<String>> obtenerMapaProductos(List<String> categorias){
        SQLiteDatabase db = this.getWritableDatabase();

        HashMap<String, List<String>> mapaProductos = new HashMap<>();

        for (int i = 0; i < categorias.size(); i++){
            List<String> nombresProductos = new ArrayList<>();

            String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS
                    + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA + " = " + "\"" + categorias.get(i) + "\"";

            Cursor productos = db.rawQuery(query, null);

            productos.moveToFirst();

            while (productos.moveToNext()){

                ProductoInventario producto = new ProductoInventario();

                producto.setNombre(productos.getString(3));

                nombresProductos.add(producto.getNombre());
            }

            mapaProductos.put(categorias.get(i), nombresProductos);
        }

        return mapaProductos;
    }

    public ArrayList<ProductoInventario> obtenerProductosAgregados(Activity activity){
        ArrayList<String> nombres = activity.getIntent().getStringArrayListExtra("nombres_productos");
        ArrayList<String> cantidades = activity.getIntent().getStringArrayListExtra("cantidades_productos");
        ArrayList<Integer> precios = activity.getIntent().getIntegerArrayListExtra("precios_productos");
        ArrayList<ProductoInventario> productos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        if (nombres != null){
            for (int i = 0; i < nombres.size(); i++){
                ProductoInventario producto = new ProductoInventario();

                String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " = " + "\"" + nombres.get(i) + "\"";

                Cursor cursorProductos = db.rawQuery(query, null);

                cursorProductos.moveToFirst();

                producto.setCategoria(cursorProductos.getString(1));
                producto.setSubCategoria(cursorProductos.getString(2));
                producto.setNombre(cursorProductos.getString(3));
                producto.setCantidad(Integer.parseInt(cantidades.get(i)));
                producto.setPrecioVenta(precios.get(i));

                productos.add(producto);
            }
        }

        return productos;
    }

    public ArrayList<ProductoInventario> obtenerProductosCategoria(String categoria){
        ArrayList<ProductoInventario> productos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_CATEGORIA + " = " + "\"" + categoria + "\"";

        Cursor productosC = db.rawQuery(query, null);

        while(productosC.moveToNext()){
            ProductoInventario producto = new ProductoInventario();

            producto.setId(productosC.getInt(0));
            producto.setCategoria(productosC.getString(1));
            producto.setSubCategoria(productosC.getString(2));
            producto.setNombre(productosC.getString(3));

            productos.add(producto);
        }

        db.close();

        return productos;
    }

    public void actualizarInventarioPedido(HashMap<String, Integer> productos){
        SQLiteDatabase db = this.getWritableDatabase();
        Object[] nombres = productos.keySet().toArray();
        for (int i = 0; i < productos.size(); i++){
            String query = "SELECT " + ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD + " FROM " + ConstantesBaseDatos.TABLA_PRODUCTOS
                    + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " = " + "\"" + (String) nombres[i] + "\"";
            Cursor producto = db.rawQuery(query, null);

            producto.moveToFirst();

            int cantidad = producto.getInt(0);
            cantidad -= productos.get((String) nombres[i]);

            String queryModificar = "UPDATE " + ConstantesBaseDatos.TABLA_PRODUCTOS
                    + " SET " + ConstantesBaseDatos.TABLA_PRODUCTOS_CANTIDAD + " = " + cantidad
                    + " WHERE " + ConstantesBaseDatos.TABLA_PRODUCTOS_NOMBRE + " = " + "\"" + (String) nombres[i] + "\"";

            db.execSQL(queryModificar);
        }
    }

    /*CLIENTES*/
    public ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_CLIENTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor clientesCursor = db.rawQuery(query, null);

        while(clientesCursor.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setId(clientesCursor.getInt(0));
            cliente.setNombre(clientesCursor.getString(1));
            cliente.setCorreo(clientesCursor.getString(2));

            clientes.add(cliente);
        }

        db.close();

        return clientes;
    }

    public void insertarClientes(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_CLIENTES, null, contentValues);
        db.close();
    }

    public HashMap<String, List<String>> obtenerMapaClientes(List<String> grupos){
        HashMap<String, List<String>> mapaClientes = new HashMap<>();
        List<String> clientes = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_CLIENTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor clientesCursor = db.rawQuery(query, null);

        while(clientesCursor.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setId(clientesCursor.getInt(0));
            cliente.setNombre(clientesCursor.getString(1));
            cliente.setCorreo(clientesCursor.getString(2));

            clientes.add(cliente.getNombre());
        }

        mapaClientes.put(grupos.get(0), clientes);

        db.close();

        return mapaClientes;
    }
}
